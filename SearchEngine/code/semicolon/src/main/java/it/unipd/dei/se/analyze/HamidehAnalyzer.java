package it.unipd.dei.se.analyze;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;

import java.io.IOException;

public class HamidehAnalyzer {

    public Analyzer createComponents(String type) throws IOException {
        Analyzer analyzer = null;
        switch (type) {
            case "type1" ->
                //filters: LowerCaseFilter,Custom StopFilter, PorterStemFilter, keywordMarker, keywordRepeat
                    analyzer = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class)
                            .addTokenFilter("lowercase")
                            .addTokenFilter("stop", "ignoreCase", "false", "words", "en-stopword.txt", "format", "wordset")
                            .addTokenFilter("porterstem")
                            .addTokenFilter("keywordMarker")
                            .addTokenFilter("keywordRepeat")
                            .build();

            case "type2" ->
                //filters: LowerCaseFilter, Custom StopFilter, englishMinimalStemFilter, keywordMarker, keywordRepeat
                    analyzer = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class)
                            .addTokenFilter("lowercase")
                            .addTokenFilter("stop", "ignoreCase", "false", "words", "en-stopword.txt", "format", "wordset")
                            .addTokenFilter("englishMinimalStem")
                            .addTokenFilter("keywordMarker")
                            .addTokenFilter("keywordRepeat")
                            .build();
            case "nlp" ->
                // it uses pos filter with loc and time and money and etc filter on each token
                    analyzer = new NLPanalyzer();
            default -> System.out.println("Invalid type. Valid Types: type1, type2, nlp ");
        }

        return analyzer;
    }


}
