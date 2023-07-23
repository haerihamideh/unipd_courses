package it.unipd.dei.se.analyze;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.tartarus.snowball.ext.EnglishStemmer;

public class Porter2Analyzer extends Analyzer{

    @Override
    protected Analyzer.TokenStreamComponents createComponents(String s) {
        StandardTokenizer tokenizer = new StandardTokenizer();
        TokenStream filter = new StopFilter(tokenizer, EnglishAnalyzer.getDefaultStopSet());
        filter = new LowerCaseFilter(filter);
        filter = new SnowballFilter(filter, new EnglishStemmer());
        return new TokenStreamComponents(tokenizer, filter);
    }
}


