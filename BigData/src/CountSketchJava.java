import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import java.util.*;
import java.util.concurrent.Semaphore;

public class CountSketchJava {
    public static final int THRESHOLD = 10000000;
    public static final int P = 8191;

    public static void main(String[] args) throws Exception {
        if (args.length != 6) {
            throw new IllegalArgumentException("USAGE: D W left right K port");
        }

        int D = Integer.parseInt(args[0]);
        int W = Integer.parseInt(args[1]);
        int left = Integer.parseInt(args[2]);
        int right = Integer.parseInt(args[3]);
        int K = Integer.parseInt(args[4]);
        int portExp = Integer.parseInt(args[5]);

        SparkConf conf = new SparkConf(true)
                .setMaster("local[*]")
                .setAppName("HW3");

        JavaStreamingContext sc = new JavaStreamingContext(conf, Durations.milliseconds(100));
        sc.sparkContext().setLogLevel("ERROR");

        Semaphore stoppingSemaphore = new Semaphore(1);
        stoppingSemaphore.acquire();

        long[] streamLength = new long[1];
        streamLength[0] = 0L;
        HashMap<Long, Long> histogram = new HashMap<>();

        long[][] countSketch = new long[D][W];
        int[][] hashA = new int[D][W];
        int[][] hashB = new int[D][W];

        Random random = new Random();
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                hashA[i][j] = random.nextInt(P - 1) + 1;
                hashB[i][j] = random.nextInt(P);
            }
        }

        sc.socketTextStream("algo.dei.unipd.it", portExp)
                .foreachRDD((batch, time) -> {
                    if (streamLength[0] < THRESHOLD) {
                        long batchSize = batch.count();
                        streamLength[0] += batchSize;

                        List<Long> batchItems = batch
                                .map(s -> Long.parseLong(s))
                                .filter(s -> s >= left && s <= right)
                                .collect();

                        for (Long item : batchItems) {
                            for (int i = 0; i < D; i++) {
                                int column = (hashA[i][0] * item.intValue() + hashB[i][0]) % P % W;
                                countSketch[i][column]++;
                            }
                            histogram.put(item, histogram.getOrDefault(item, 0L) + 1);
                        }

                        if (streamLength[0] >= THRESHOLD) {
                            stoppingSemaphore.release();
                        }
                    }
                });

        System.out.println("Starting streaming engine");
        sc.start();
        System.out.println("Waiting for shutdown condition");
        stoppingSemaphore.acquire();
        System.out.println("Stopping the streaming engine");
        sc.stop(false, false);
        System.out.println("Streaming engine stopped");

        // Calculate statistics
        long sumSquaredFrequencies = histogram.values().stream().mapToLong(f -> f * f).sum();
        double normalizedF2 = (double) sumSquaredFrequencies / (histogram.size() * histogram.size());

        long sumSquaredCountSketch = 0;
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < W; j++) {
                sumSquaredCountSketch += countSketch[i][j] * countSketch[i][j];
            }
        }
        double approxNormalizedF2 = (double) sumSquaredCountSketch / (D * W);

        List<Map.Entry<Long, Long>> topKFrequentItems = new ArrayList<>(histogram.entrySet());
        topKFrequentItems.sort((e1, e2) -> -Long.compare(e1.getValue(), e2.getValue()));
        topKFrequentItems = topKFrequentItems.subList(0, Math.min(K, topKFrequentItems.size()));

        double sumRelativeError = 0;
        for (Map.Entry<Long, Long> entry : topKFrequentItems) {
            long item = entry.getKey();
            long trueFrequency = entry.getValue();

            long minEstimate = Long.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                int column = (int) ((hashA[i][0] * item + hashB[i][0]) % P % W);
                long estimate = countSketch[i][column];
                minEstimate = Math.min(minEstimate, estimate);
            }

            double relativeError = (double) (minEstimate - trueFrequency) / trueFrequency;
            sumRelativeError += relativeError;
        }

        double averageRelativeError = sumRelativeError / topKFrequentItems.size();

        // Print results
        System.out.println("Normalized F2 (exact): " + normalizedF2);
        System.out.println("Normalized F2 (approximate): " + approxNormalizedF2);
        System.out.println("Average relative error: " + averageRelativeError);
    }
}