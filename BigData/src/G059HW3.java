import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.*;
import java.util.concurrent.Semaphore;

public class G059HW3{
    private static final int THRESHOLD = 10000000;
    private static final int P = 8191;

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
                .setAppName("G059HW3");
        JavaStreamingContext sc = new JavaStreamingContext(conf, Durations.milliseconds(100));
        sc.sparkContext().setLogLevel("ERROR");

        Semaphore stoppingSemaphore = new Semaphore(1);
        stoppingSemaphore.acquire();
        long[] streamLength = new long[1];
        streamLength[0] = 0L;

        System.out.println("Receiving data from port = " + portExp);

        // Initialize Count Sketch and helper structures
        long[][] countSketch = new long[D][W];
        long[] aValues = new long[D];
        long[] bValues = new long[D];
        Random random = new Random();
        for (int i = 0; i < D; i++) {
            aValues[i] = random.nextInt(P - 1) + 1;
            bValues[i] = random.nextInt(P);
        }

        // Initialize the histogram
        Long2LongOpenHashMap histogram = new Long2LongOpenHashMap();

        // Define DStream
        JavaDStream<String> inputStream = sc.socketTextStream("algo.dei.unipd.it", portExp, StorageLevel.MEMORY_AND_DISK());

        inputStream.foreachRDD((batch, time) -> {
            if (streamLength[0] < THRESHOLD) {
                long batchSize = batch.count();
                streamLength[0] += batchSize;

                JavaPairRDD<Long, Long> batchItems = batch
                        .mapToPair(s -> new Tuple2<>(Long.parseLong(s), 1L))
                        .reduceByKey((i1, i2) -> i1 + i2)
                        .filter(pair -> pair._1 >= left && pair._1 <= right);

                Map<Long, Long> batchItemsMap = batchItems.collectAsMap();

                updateHistogramAndCountSketch(histogram, countSketch, batchItemsMap, D, W, aValues, bValues);

                if (batchSize > 0) {
                    System.out.println("Batch size at time [" + time + "] is: " + batchSize);
                }

                if (streamLength[0] >= THRESHOLD) {
                    stoppingSemaphore.release();
                }
            }
        });

        startAndStopStreaming(sc, stoppingSemaphore);

        computeAndPrintResults(histogram, countSketch, D, W, aValues, bValues, K);
    }

    private static void updateHistogramAndCountSketch(Long2LongOpenHashMap histogram, long[][] countSketch, Map<Long, Long> batchItemsMap,
                                                      int D, int W, long[] aValues, long[] bValues) {
        for (Map.Entry<Long, Long> pair : batchItemsMap.entrySet()) {
            long item = pair.getKey();
            long freq = pair.getValue();

            histogram.put(item, histogram.getOrDefault(item, 0L) + freq);

            for (int i = 0; i < D; i++) {
                int columnIndex = (int) (((aValues[i] * item + bValues[i]) % P) % W);
                countSketch[i][columnIndex] += freq;
            }
        }
    }

    private static void startAndStopStreaming(JavaStreamingContext sc, Semaphore stoppingSemaphore) throws InterruptedException {
        System.out.println("Starting streaming engine");
        sc.start();
        System.out.println("Waiting for shutdown condition");
        stoppingSemaphore.acquire();
        System.out.println("Stopping the streaming engine");
        sc.stop(false, false);
        System.out.println("Streaming engine stopped");
    }

    private static void computeAndPrintResults(Long2LongOpenHashMap histogram, long[][] countSketch, int D, int W, long[] aValues, long[] bValues, int K) {
        long sumR = 0;
        long F2 = 0;
        long F2Estimate = 0;
        double avgError = 0;
        PriorityQueue<Map.Entry<Long, Long>> topKFrequencies = new PriorityQueue<>(Comparator.comparingLong(Map.Entry::getValue));

        for (Map.Entry<Long, Long> pair : histogram.entrySet()) {
            long item = pair.getKey();
            long freq = pair.getValue();
            sumR += freq;
            F2 += (freq * freq)/(sumR^2);

            // Find estimated frequency
            long minEstimate = Long.MAX_VALUE;
            for (int i = 0; i < D; i++) {
                int columnIndex = (int) (((aValues[i] * item + bValues[i]) % P) % W);
                minEstimate = Math.min(minEstimate, countSketch[i][columnIndex]);
            }

            F2Estimate += (minEstimate * minEstimate)/(sumR^2);
            avgError += Math.abs(freq - minEstimate) / (double) freq;

            // Add item to top K frequencies
            topKFrequencies.add(new AbstractMap.SimpleEntry<>(item, minEstimate));
            if (topKFrequencies.size() > K) {
                topKFrequencies.poll();
            }
        }

        avgError /= histogram.size();

        System.out.println("F2: " + F2);
        System.out.println("F2 Estimate: " + F2Estimate);
        System.out.println("Average Relative Error: " + avgError);

        System.out.println("Top " + K + " items:");
        while (!topKFrequencies.isEmpty()) {
            Map.Entry<Long, Long> entry = topKFrequencies.poll();
            System.out.println("Item: " + entry.getKey() + ", Estimated frequency: " + entry.getValue());
        }

        System.out.println("Total number of processed items: " + sumR);
    }
}