package it.unipd.dei.se;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RankFusion {
    private static final int MAX_RESULTS = 1000;

    public static void main(String[] args) {

//        String lang = "French";
        String lang = "English";

        // Define the two retrieval run IDs
        //French

        /*String fusedRankName = "seupd2223-semicolon-FusedRankFrenchAnalyzer-Fr";
        List<String> runIds = List.of(
                "seupd2223-semicolon-FrAnalyzerDefaultStp-Fr",
                "seupd2223-semicolon-FrenchAnalyzerFrStop-Fr",
                "seupd2223-semicolon-FrenchAnalyzerFrStopEng-Fr",
                "seupd2223-semicolon-FrenchAnalyzerFrStopNum-Fr",
                "seupd2223-semicolon-FrenchAnalyzerFrStopwordFr",


                );*/


        //English
        //All except drichlet and synonyms
        String fusedRankName = "seupd2223-semicolon-FusedRankAllExcpSynNDrich-Eng";
        List<String> runIds = List.of(
                "fpga_analyzer_fpga_searcher_ID",
                "KStemFilterAnalyzer_ID",
                "seupd2223-semicolon-KStemAnalyzer3-4-english",
                "seupd2223-semicolon-KStemAnalyzer3-4-whitespace",
                "seupd2223-semicolon-ngramAnalyzer3-4-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer-english",
                "seupd2223-semicolon-porter2stem-english",
                "seupd2223-semicolon-porterstem2-english",
                "seupd2223-semicolon-porterstem2-english-1p4",
                "seupd2223-semicolon-porterstem2-english-1p8",
                "seupd2223-semicolon-porterstem2-english-3p5",
                "seupd2223-semicolon-porterstem-english",
                "seupd2223-semicolon-porterstem-english1p3",
                "seupd2223-semicolon-snowballPorter3-4-standard",
                "seupd2223-semicolon-basic-eng"
        );

        //All the almost good runs
        /*String fusedRankName = "seupd2223-semicolon-FusedRankAboveThreshold-Eng";
        List<String> runIds = List.of(
                "seupd2223-semicolon-ngramAnalyzer3-4-english",
                "seupd2223-semicolon-porter2stem-english",
                "seupd2223-semicolon-porterstem2-english",
                "seupd2223-semicolon-porterstem2-english-1p4",
                "seupd2223-semicolon-porterstem2-english-1p8",
                "seupd2223-semicolon-porterstem-english",
                "seupd2223-semicolon-porterstem-english1p3"
        );*/

        //All the runs
        /*String fusedRankName = "seupd2223-semicolon-FusedRankAll-Eng";
        List<String> runIds = List.of(
                "fpga_analyzer_fpga_searcher_ID",
                "KStemFilterAnalyzer_ID",
                "semicolon-custom-with-syn1-idx-custom-with-syn1-srch",
                "seupd2223-semicolon-KStemAnalyzer3-4-english",
                "seupd2223-semicolon-KStemAnalyzer3-4-whitespace",
                "seupd2223-semicolon-ngram3-4-Dirichlet",
                "seupd2223-semicolon-ngramAnalyzer3-4-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer-english",
                "seupd2223-semicolon-porter2stem-english",
                "seupd2223-semicolon-porterstem2-english",
                "seupd2223-semicolon-porterstem2-english-1p4",
                "seupd2223-semicolon-porterstem2-english-1p8",
                "seupd2223-semicolon-porterstem2-english-3p5",
                "seupd2223-semicolon-porterstem-drichlet",
                "seupd2223-semicolon-porterstem-drichlet-1000",
                "seupd2223-semicolon-porterstem-english",
                "seupd2223-semicolon-porterstem-english1p3",
                "seupd2223-semicolon-snowballPorter3-4-standard",
                "seupd2223-semicolon-basic-eng"
        );*/

        //All except dirichlet and 3.5
        /*String fusedRankName = "seupd2223-semicolon-FusedRankAllExceptDirichlet-Eng";
        List<String> runIds = List.of(
                "seupd2223-semicolon-KStemAnalyzer3-4-english",
                "seupd2223-semicolon-KStemAnalyzer3-4-whitespace",
                "seupd2223-semicolon-ngram3-4-Dirichlet",
                "seupd2223-semicolon-ngramAnalyzer3-4-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer4-5-english",
                "seupd2223-semicolon-ngramAnalyzer-english",
                "seupd2223-semicolon-porter2stem-english",
                "seupd2223-semicolon-porterstem2-english",
                "seupd2223-semicolon-porterstem2-english-1p4",
                "seupd2223-semicolon-porterstem2-english-1p8",
                "seupd2223-semicolon-porterstem-english",
                "seupd2223-semicolon-porterstem-english1p3",
                "seupd2223-semicolon-snowballPorter3-4-standard",
                "seupd2223-semicolon-basic-eng"
        );*/



        String base = "code/out/"+lang+"/fused/";
        String outputFilePath = base+fusedRankName+".txt";

        // Read the ranking lists for all runs and store them in a map
        Map<String, Map<String, Double>> queryIdToRankListMap = new HashMap<>();
        for (String runId : runIds) {
            Map<String, Map<String, Double>> tmpQueryIdToRankListMap = extractColumnsFromFile(base + runId + ".txt");
            for (String queryId : tmpQueryIdToRankListMap.keySet()) {
                Map<String, Double> scoreMap = tmpQueryIdToRankListMap.getOrDefault(queryId, new HashMap<>());
                Map<String, Double> rankList = queryIdToRankListMap.getOrDefault(queryId, new HashMap<>());
                for (String docId : scoreMap.keySet()) {
                    double score = scoreMap.get(docId);
                    if (rankList.containsKey(docId)) {
                        rankList.put(docId, rankList.get(docId) + score);
                    } else {
                        rankList.put(docId, score);
                    }
                }
                queryIdToRankListMap.put(queryId, rankList);
            }
        }
        // Calculate weights for each run
        double alpha = 0.5;
        double beta = 0.5;

        //Todo: the weight of each run
        /*for (String runId : runIds) {
            switch (runId) {
                case "seupd2223-semicolon-basic-french":
                    beta = 0.7;
                    break;
                case "semicolon-stop-french":
                    beta = 0.8;
                    break;
                case "seupd2223-semicolon-basic-engstp-fr":
                    beta = 0.8;
                    break;
                case "seupd2223-semicolon-basic-engstp-fr1p4p75":
                    break;
                case "seupd2223-semicolon-basic-french-newstop":
                    beta = 0.8;
                    break;
                case"seupd2223-semicolon-basic-french-stop":
                    break;
                case "stem-engstp-MyFrenchAnalyzer":
                    break;
                case "stem-engstp-MyFrenchAnalyzer1p3k1":
                    break;
                default:
                    break;
            }
        }*/
        // Write the fused rankings for this queryId to output file
        try (PrintWriter out = new PrintWriter(outputFilePath)) {
            // Compute fused rankings
            for (String queryId : queryIdToRankListMap.keySet()) {
                Map<String, Double> rankList = queryIdToRankListMap.get(queryId);
                double maxRankScore = rankList.isEmpty() ? 1.0 : Collections.max(rankList.values());
                double minRankScore = rankList.isEmpty() ? 0.0 : Collections.min(rankList.values());
                double sumWeights = alpha + beta;

                Map<String, Double> fusedRankings = new HashMap<>();

                for (String docId : rankList.keySet()) {
                    //Todo maybe a  better way?
                    double normScore = (alpha * (rankList.getOrDefault(docId, 0.0) - minRankScore) /
                            (maxRankScore - minRankScore))
                            + beta * (rankList.getOrDefault(docId, 0.0) / rankList.size());
                    fusedRankings.put(docId, normScore);
                }

                // Sort the fused rankings and retain the top K results
                LinkedHashMap<String, Double> sortedRankings = fusedRankings.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(MAX_RESULTS)
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


                int rank = 0;
                for (String docId : sortedRankings.keySet()) {
                    double score = sortedRankings.get(docId);
                    out.println(queryId + "\tQ0\t" + docId + "\t" + rank + "\t" + score + "\t"+ fusedRankName);
                    rank++;
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: Output file " + outputFilePath + " not found.");
            e.printStackTrace();
        }
    }

    private static Map<String, Map<String, Double>> extractColumnsFromFile(String filePath) {
        Map<String, Map<String, Double>> queryIdToRankListMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cols = line.split("\t");
                if (cols.length == 6) { // Assumes the ranking list file has 6 columns
                    String queryId = cols[0];
                    String docId = cols[2];
                    double score = Double.parseDouble(cols[4]);
                    Map<String, Double> scoreMap = queryIdToRankListMap.getOrDefault(queryId, new HashMap<>());
                    scoreMap.put(docId, score);
                    queryIdToRankListMap.put(queryId, scoreMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryIdToRankListMap;
    }


}