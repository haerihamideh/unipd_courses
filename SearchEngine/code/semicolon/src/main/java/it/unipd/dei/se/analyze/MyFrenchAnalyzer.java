package it.unipd.dei.se.analyze;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.tartarus.snowball.ext.FrenchStemmer;

import java.util.Collections;
import java.util.Objects;

public class MyFrenchAnalyzer extends Analyzer {
    private final CharArraySet stopWords;

    public MyFrenchAnalyzer(CharArraySet stopWords) {
        Objects.requireNonNull(stopWords, "stopWords cannot be null");
        this.stopWords = stopWords;
    }

    public MyFrenchAnalyzer(String... stopWords) {
        this(CharArraySet.copy(Collections.singleton(stopWords)));
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final StandardTokenizer tokenizer = new StandardTokenizer();
        TokenStream tok = new StopFilter(tokenizer, stopWords);
        tok = new SnowballFilter(tok, new FrenchStemmer());
        tok = new LowerCaseFilter(tok);
        return new TokenStreamComponents(tokenizer, tok);
    }

}