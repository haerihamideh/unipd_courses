/*
 * Copyright 2021-2022 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.unipd.dei.se;


import it.unipd.dei.se.analyze.NGramAnalyzer;
import it.unipd.dei.se.analyze.Porter2Analyzer;
import it.unipd.dei.se.analyze.MyFrenchAnalyzer;
import it.unipd.dei.se.index.DirectoryIndexer;
import it.unipd.dei.se.parse.Eliminator;
import it.unipd.dei.se.parse.QwantParser;
import it.unipd.dei.se.search.Searcher;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.tartarus.snowball.ext.FrenchStemmer;


import java.io.File;
import java.util.Map;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Introductory example on how to use <a href="https://lucene.apache.org/" target="_blank">Apache Lucene</a> to index
 * and search the TIPSTER corpus.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.0
 * @since 1.0
 */
public class runFile {

    public static CharArraySet loadStopwordSet(String stopwordFile) throws IOException {
        Path stopwordPath = Paths.get(stopwordFile);
        return WordlistLoader.getWordSet(
                new InputStreamReader(Files.newInputStream(stopwordPath), StandardCharsets.UTF_8));
    }

    public static boolean areDirectoriesEqual(String directoryPath1, String directoryPath2) {
        File[] files1 = new File(directoryPath1).listFiles();
        File[] files2 = new File(directoryPath2).listFiles();

        if (files1.length != files2.length) {
            return false;
        }

        return true;
    }
    public static void createDirectoryIfNotExist(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            try {
                directory.mkdirs();
                System.out.printf("Directory %s created.%n", directoryPath);
            } catch (Exception e) {
                System.err.printf("Error creating directory %s: %s%n", directoryPath, e.getMessage());
            }
        } else {
            System.out.printf("Directory %s already exists.%n", directoryPath);
        }
    }
    /**
     * Main method of the class.
     *
     * @param args command line arguments. If provided, {@code args[0]} contains the path the index directory;
     *             {@code args[1]} contains the path to the run file.
     * @throws Exception if something goes wrong while indexing and searching.
     */
    public static void main(String[] args) throws Exception {
//        final String language = "English";
        final String language = "French";
        final int ramBuffer = 256;

        //Don't Change this!
        boolean writeInOneLine = false;

        String inputFolderEliminate = "code/OriginalData/"+language+"/Documents/Json"; // Change to the path of input folder
        //String inputFolder = "OriginalData/English/Documents/Json"; // Change to the path of input folder

        String suffix = !writeInOneLine ? "/Separated" : "/OneLine";
        String outputFolderEliminate = "code/ProcessedOutput/"+language + suffix;

        createDirectoryIfNotExist(outputFolderEliminate);

        if (!areDirectoriesEqual(inputFolderEliminate,outputFolderEliminate)) {
            Eliminator.sanitization(inputFolderEliminate,outputFolderEliminate,writeInOneLine);
        }

        final String docsPath = outputFolderEliminate; //"code/ProcessedOutput/"+language;
        final String indexPath = "code/semicolon/indexed/"+language;
        createDirectoryIfNotExist(indexPath);

        final String extension = "json";
        final int expectedDocs = 1570734;
        final String charsetName = "ISO-8859-1";

        /*final Analyzer a = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class).addTokenFilter(
                LowerCaseFilterFactory.class).addTokenFilter(StopFilterFactory.class).build();*/

        //Analyzer a = CustomAnalyzer.builder()
        //      .withTokenizer("standard")
        //      .addTokenFilter("lowercase")
        //      .addTokenFilter("stop")
        //      .addTokenFilter("porterstem")
        //      .addTokenFilter("capitalization")
        //      .build();


//        final Similarity sim = new BM25Similarity((float) 3.5, (float) 0.75);
//        final Similarity sim = new BM25Similarity((float) 1.3, (float) 0.75);
//        Porter2Analyzer a = new Porter2Analyzer();

        //NGramAnalyzer a = new NGramAnalyzer(3, 4);

        //KStem with Standard
        //Analyzer a = CustomAnalyzer.builder()
        //        .withTokenizer("standard")
        //        .addTokenFilter("lowercase")
        //        .addTokenFilter("stop")
        //        .addTokenFilter("kstem")
        //        .addTokenFilter("ngram", "minGramSize", "3", "maxGramSize", "4")
        //        .build();

        //KStem with Whitespace
        //Analyzer a = CustomAnalyzer.builder()
        //        .withTokenizer("whitespace")
        //        .addTokenFilter("lowercase")
        //        .addTokenFilter("stop")
        //        .addTokenFilter("kstem")
        //        .addTokenFilter("ngram", "minGramSize", "3", "maxGramSize", "4")
        //        .build();

        //Analyzer a = CustomAnalyzer.builder()
        //        .withTokenizer("standard")
        //        .addTokenFilter("lowercase")
        //        .addTokenFilter("stop")
        //        .addTokenFilter("snowballPorter")
        //        .addTokenFilter("ngram", "minGramSize", "3", "maxGramSize", "4")
        //        .build();

        //Analyzer a = CustomAnalyzer.builder()
        //        .withTokenizer("standard")
        //        .addTokenFilter("lowercase")
        //        .addTokenFilter("stop")
        //        .addTokenFilter("porterstem")
        //        .addTokenFilter("capitalization")
        //        .build();

        //final Similarity sim = new LMDirichletSimilarity(1000);

        //NGramAnalyzer a = new NGramAnalyzer(3, 4);
        //final Similarity sim = new LMDirichletSimilarity(2000);

        //final Similarity sim = new BM25Similarity((float) 1.3, (float) 0.75);

//        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/zettair.txt");
//        Analyzer a = new EnglishAnalyzer(wordsArray); //0.1448

        //FR
//        seupd2223-semicolon-FrenchAnalyzerFrStop-Fr
        final Similarity sim = new BM25Similarity();
        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/frStop.txt");
        Analyzer a = new FrenchAnalyzer(wordsArray); //0.21 without eng stop with en .2122 ndcg .3796

        //FrenchAnalyzer-with numbers included in stopword / seupd2223-semicolon-FrenchAnalyzerFrStopNum-Fr
//        final Similarity sim = new BM25Similarity();
//        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/frStopNum.txt");
//        Analyzer a = new FrenchAnalyzer(wordsArray); //0.21 without eng stop with en .2122 ndcg .3796


        //seupd2223-semicolon-FrenchAnalyzerFrStopEng-Fr
//        final Similarity sim = new BM25Similarity();
//        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/frStopEng.txt");
//        Analyzer a = new FrenchAnalyzer(wordsArray); //0.21 without eng stop with en .2122 ndcg .3796

        //seupd2223-semicolon-FrenchAnalyzerFrStopEng1p4-Fr
//        final Similarity sim = new BM25Similarity((float) 1.4,(float) 0.75);
//        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/frStopEng.txt");
//        Analyzer a = new FrenchAnalyzer(wordsArray); //0.21 without eng stop with en .2122 ndcg .3796


        //FrenchAnalyzer-fr-stopword / seupd2223-semicolon-FrenchAnalyzerFrStopwordFr
//        final Similarity sim = new BM25Similarity();
//        CharArraySet wordsArray = loadStopwordSet("code/semicolon/src/main/resources/fr-stopwords");
//        Analyzer a = new FrenchAnalyzer(wordsArray);

        //FrenchAnalyzer-with default stopword: seupd2223-semicolon-FrAnalyzerDefaultStp-fr
//        final Similarity sim = new BM25Similarity();
//        Analyzer a = new FrenchAnalyzer();

        //seupd2223-semicolon-myFrAnalyzerDefaultStp-fr
//        final Similarity sim = new BM25Similarity();
//        MyFrenchAnalyzer a = new MyFrenchAnalyzer();

        final String topics = "code/OriginalData/"+language+"/Queries/train.tsv";

        final String runPath = "code/out/"+language;
        createDirectoryIfNotExist(runPath);

        final String runID = "seupd2223-semicolon-FrenchAnalyzerFrStopEng1p4-Fr";

        final int maxDocsRetrieved = 1000;

        final int expectedTopics = 672;

        // indexing
        final DirectoryIndexer i = new DirectoryIndexer(a, sim, ramBuffer, indexPath, docsPath, extension, charsetName,
                                                        expectedDocs, QwantParser.class);
        i.index();

        // searching
        final Searcher s = new Searcher(a, sim, indexPath, topics, expectedTopics, runID, runPath, maxDocsRetrieved);
        s.search();

    }

}
