import org.apache.spark.HashPartitioner;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import scala.Tuple3;

import java.util.*;

public class G045HW2 {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("TriangleCounting").setMaster("yarn");
        conf.set("spark.locality.wait", "0s");
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        JavaSparkContext sc = new JavaSparkContext(conf);

//        // Load the input data as an RDD of edges
//        JavaRDD<String> input = sc.textFile("facebook_large.txt");
//        int C = 1; // number of colors
//        int R = 1; // Number of repeat
        // Parse command line arguments
        int C = Integer.parseInt(args[0]);
        int R = Integer.parseInt(args[1]);
        int F = Integer.parseInt(args[2]);
        String inputFile = args[3];


        // Load the input data as an RDD of edges
        JavaRDD<String> input = sc.textFile(inputFile);

        JavaPairRDD<Integer, Integer> edges = input.mapToPair(line -> {
            String[] parts = line.split(",");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            return new Tuple2<>(u, v);
        });

        long numEdges = edges.count();
        System.out.println("Dataset = " + input.toString());
        System.out.println("Number of Edges = " + numEdges);
        System.out.println("Number of Colors = " + C);
        System.out.println("Number of Repetitions = " + R);

        if(F==0) {
            long[] totalTime_color = new long[20];
            long[] t_final_color = new long[20];
            for (int i = 0; i < R; i++) {
                long startTime1 = System.currentTimeMillis();
                //count number of triangles by coloring
                t_final_color[i] = MR_ApproxTCwithNodeColors(edges, C);
                long endTime1 = System.currentTimeMillis();
                totalTime_color[i] = endTime1 - startTime1;
            }

            double median_t_final_color = getMedian(t_final_color, R);

            // Print results of MR_ApproxTCwithNodeColors
            System.out.println("Approximation through node coloring");
            System.out.println("- Number of triangles (median over " + R + " runs) = " + (int) median_t_final_color);
            System.out.println("- Running time (average over " + R + " runs) = " + Arrays.stream(totalTime_color).average() + " ms");
        }
        else {
            long[] totalTime_ExactTC = new long[20];
            long[] t_final_ExactTC = new long[20];
            for (int i = 0; i < R; i++) {
                long startTime2 = System.currentTimeMillis();
                //count number of triangles by coloring
                t_final_ExactTC[i] = MR_ExactTC(edges, C);
                long endTime2 = System.currentTimeMillis();
                totalTime_ExactTC[i] = endTime2 - startTime2;
            }

            double median_t_final_ExactTC = getMedian(t_final_ExactTC, R);
            // Print results of MR_ExactTC
            System.out.println("Approximation through node ExactTC");
            System.out.println("- Number of triangles = " + (int) median_t_final_ExactTC);
            System.out.println("- Running time = " + Arrays.stream(totalTime_ExactTC).average() + " ms");
        }
        sc.stop();
    }



    //C : number of colors
    //count number of triangles by coloring
    public static Long MR_ApproxTCwithNodeColors(JavaPairRDD<Integer, Integer> edges, int C) {
        Random rand = new Random();
        int p = 8191; // prime number
        int a = rand.nextInt(p - 1) + 1;/// random integer in [1, p-1]
        int b = rand.nextInt(p); // random integer in [0, p-1]

        // Partition the edges by color using the hash function
        JavaPairRDD<Integer, Integer> repartition = edges.repartition(32).cache();
        edges = repartition;
        JavaPairRDD<Integer, Tuple2<Integer, Integer>> coloredEdges = edges.flatMapToPair(edge -> {
            int u = edge._1();
            int v = edge._2();
            int color = ((a * u + b) % p) % C;
            if (color == ((a * v + b) % p) % C) {
                return Collections.singletonList(new Tuple2<>(color, new Tuple2<>(u, v))).iterator();
            } else {
                return Collections.emptyIterator();
            }
        }).partitionBy(new HashPartitioner(C));

        // Compute the number of triangles for each color
        JavaPairRDD<Integer, Long> triangleCounts = coloredEdges.groupByKey().mapValues(edgeList -> {
            // Create an adjacency list for the vertices in the edge set
            Map<Integer, Set<Integer>> adjacencyLists = new HashMap<>();
            for (Tuple2<Integer, Integer> edge : edgeList) {
                int u = edge._1();
                int v = edge._2();
                adjacencyLists.computeIfAbsent(u, k -> new HashSet<>()).add(v);
                adjacencyLists.computeIfAbsent(v, k -> new HashSet<>()).add(u);
            }

            // Count the number of triangles in the edge set
            long numTriangles = 0;
            for (int u : adjacencyLists.keySet()) {
                Set<Integer> uNeighbors = adjacencyLists.get(u);
                for (int v : uNeighbors) {
                    if (v > u) {
                        Set<Integer> vNeighbors = adjacencyLists.get(v);
                        for (int w : vNeighbors) {
                            if (w > v && uNeighbors.contains(w)) {
                                numTriangles++;
                            }
                        }
                    }
                }
            }

            return numTriangles;
        });

        // Print the triangle counts for each color
        Long t = 0l;
        List<Tuple2<Integer, Long>> counts = triangleCounts.collect();
        for (Tuple2<Integer, Long> count : counts) {
            t += count._2();
            //System.out.println("Color " + count._1() + ": " + count._2());
        }
        Long t_final = (C * C) * t;

        return t_final;
    }


public static Long MR_ExactTC(JavaPairRDD<Integer, Integer> edges, int C) {
    Random random = new Random();
    int prime = 8191;
    int a = random.nextInt(prime - 1) + 1;
    int b = random.nextInt(prime);

    //Round 1
    //Reads the input graph into an RDD of strings (called rawData) and transform it into an RDD of edges (called edges), 
    //represented as pairs of integers, partitioned into 32 partitions, and cached
    JavaPairRDD<Integer, Integer> partitionedEdges = edges.repartition(32).cache();
    JavaPairRDD<Tuple3<Integer, Integer, Integer>, Tuple2<Integer, Integer>> transformedEdges = partitionedEdges.flatMapToPair(edge -> {
        int x = edge._1;
        int y = edge._2;

        int hashX = ((a * x + b) % prime) % C;
        int hashY = ((a * y + b) % prime) % C;
        List<Tuple2<Tuple3<Integer, Integer, Integer>, Tuple2<Integer, Integer>>> result = new ArrayList<>();
        for (int i = 0; i < C; i++) {
            int[] keyArray = new int[]{hashY, hashX, i};
            Arrays.sort(keyArray);
            result.add(new Tuple2<>(new Tuple3<>(keyArray[0], keyArray[1], keyArray[2]), edge));
        }
        return result.iterator();
    }).cache();

    JavaPairRDD<Tuple3<Integer, Integer, Integer>, Iterable<Tuple2<Integer, Integer>>> groupedEdges = transformedEdges.groupByKey().cache();

    //compute the number of triangles 
    JavaPairRDD<Tuple3<Integer, Integer, Integer>, Long> triangleCounts = groupedEdges.mapToPair(entry -> {
        Tuple3<Integer, Integer, Integer> key = entry._1;
        Iterable<Tuple2<Integer, Integer>> values = entry._2;
        ArrayList<Tuple2<Integer, Integer>> edgeList = new ArrayList<>();
        for (Tuple2<Integer, Integer> edge : values) {
            edgeList.add(edge);
        }
        Long count = CountTriangles2(edgeList, key, a, b, prime, C);
        return new Tuple2<>(key, count);
    });

    //Round 2. Compute and output the sum of all triangles determined in Round 1
    Long total = triangleCounts.values().reduce((x, y) -> x + y);

    return total;
}


    public static double getMedian(long[] arr,int R) {
        // Sort the array in ascending order.
        Arrays.sort(arr,0,R);

        // Find the last non-zero element in the sorted array.
        int i = R - 1;
        while (i >= 0 && arr[i] == 0) {
            i--;
        }
        // If there are no non-zero elements, return 0 as the median.
        if (i < 0) {
            return 0;
        }
        // If there is only one non-zero element, return that element as the median.
        if (i == 0) {
            return arr[0];
        }
        // If there are an odd number of non-zero elements, return the middle element as the median.
        if (i % 2 == 0) {
            int mid = i / 2;
            return arr[mid];
        }
        // If there are an even number of non-zero elements, return the average of the two middle elements as the median.
        else {
            int mid1 = i / 2;
            int mid2 = mid1 + 1;
            return (arr[mid1] + arr[mid2]) / 2.0;
        }
    }

    public static Long CountTriangles2(ArrayList<Tuple2<Integer, Integer>> edgeSet, Tuple3<Integer, Integer, Integer> key, long a, long b, long p, int C) {
        if (edgeSet.size()<3) return 0L;
        HashMap<Integer, HashMap<Integer,Boolean>> adjacencyLists = new HashMap<>();
        HashMap<Integer, Integer> vertexColors = new HashMap<>();
        for (int i = 0; i < edgeSet.size(); i++) {
            Tuple2<Integer,Integer> edge = edgeSet.get(i);
            int u = edge._1();
            int v = edge._2();
            if (vertexColors.get(u) == null) {vertexColors.put(u, (int) ((a*u+b)%p)%C);}
            if (vertexColors.get(v) == null) {vertexColors.put(v, (int) ((a*v+b)%p)%C);}
            HashMap<Integer,Boolean> uAdj = adjacencyLists.get(u);
            HashMap<Integer,Boolean> vAdj = adjacencyLists.get(v);
            if (uAdj == null) {uAdj = new HashMap<>();}
            uAdj.put(v,true);
            adjacencyLists.put(u,uAdj);
            if (vAdj == null) {vAdj = new HashMap<>();}
            vAdj.put(u,true);
            adjacencyLists.put(v,vAdj);
        }
        Long numTriangles = 0L;
        for (int u : adjacencyLists.keySet()) {
            HashMap<Integer,Boolean> uAdj = adjacencyLists.get(u);
            for (int v : uAdj.keySet()) {
                if (v>u) {
                    HashMap<Integer,Boolean> vAdj = adjacencyLists.get(v);
                    for (int w : vAdj.keySet()) {
                        if (w>v && (uAdj.get(w)!=null)) {
                            ArrayList<Integer> tcol = new ArrayList<>();
                            tcol.add(vertexColors.get(u));
                            tcol.add(vertexColors.get(v));
                            tcol.add(vertexColors.get(w));
                            Collections.sort(tcol);
                            boolean condition = (tcol.get(0).equals(key._1())) && (tcol.get(1).equals(key._2())) && (tcol.get(2).equals(key._3()));
                            if (condition) {numTriangles++;}
                        }
                    }
                }
            }
        }
        return numTriangles;
    }

}

