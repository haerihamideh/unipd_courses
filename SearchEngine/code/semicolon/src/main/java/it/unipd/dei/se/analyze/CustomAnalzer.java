package it.unipd.dei.se.analyze;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.synonym.WordnetSynonymParser;
import org.apache.lucene.util.CharsRef;
import org.tartarus.snowball.ext.EnglishStemmer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

public class CustomAnalzer extends Analyzer {

    private final SynonymMap synonymMap;
    private final CharArraySet stopwordSet;

    public CustomAnalzer(String synonymFile, String stopwordFile) throws IOException, ParseException {
        this.synonymMap = buildSynonymMap(synonymFile);
        this.stopwordSet = loadStopwordSet(stopwordFile);
    }

    private SynonymMap buildSynonymMap(String synonymFile) throws IOException {
        Path synonymPath = Paths.get(synonymFile);
        SynonymMap.Builder builder = new SynonymMap.Builder();

        // Read the synonym file and add each group of synonyms to the builder
        Files.lines(synonymPath, StandardCharsets.UTF_8).forEach(line -> {
            String[] tokens = line.split(",");
            for (int i = 0; i < tokens.length; i++) {
                for (int j = i + 1; j < tokens.length; j++) {
                    builder.add(new CharsRef(tokens[i]), new CharsRef(tokens[j]), true);
                    builder.add(new CharsRef(tokens[j]), new CharsRef(tokens[i]), true);
                }
            }
        });

        return builder.build();
    }

    private CharArraySet loadStopwordSet(String stopwordFile) throws IOException {
        Path stopwordPath = Paths.get(stopwordFile);
        return WordlistLoader.getWordSet(
                new InputStreamReader(Files.newInputStream(stopwordPath), StandardCharsets.UTF_8));
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream tokens;
        tokens = new LowerCaseFilter(source);
        tokens = new StopFilter(tokens, stopwordSet);
        tokens = new SynonymFilter(tokens, synonymMap, true);
        tokens = new SnowballFilter(tokens, new EnglishStemmer());
//        tokens = new LovinsStemFilter(tokens);

        return new TokenStreamComponents(source, tokens);
    }
}