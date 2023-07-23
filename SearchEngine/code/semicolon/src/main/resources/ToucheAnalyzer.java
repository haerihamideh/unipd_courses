
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.shingle.ShingleFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import src.main.parse.ParsedDocument;
import src.main.parse.ParsedSentence;

import java.io.IOException;
import java.io.Reader;

import static src.main.analyze.AnalyzerUtil.consumeTokenStream;
import static src.main.analyze.AnalyzerUtil.loadStopList;

/**
 * Custom {@link Analyzer} specifically developed for touche task 1.
 * This work is largely based on what Professor Nicola Ferro presented during the lectures of the course.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.0
 * @since 1.0
 */
public class ToucheAnalyzer extends Analyzer {

    /**
     * Creates a new instance of the analyzer for touche'.
     */
    public ToucheAnalyzer() {
        super();
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream tokens = new LowerCaseFilter(source);

        if(fieldName.equals(ParsedDocument.FIELDS.TEXT) || fieldName.equals(ParsedDocument.FIELDS.CONCLUSION) || fieldName.equals(ParsedSentence.FIELDS.SENT_TEXT)){
            tokens = new StopFilter(tokens, loadStopList("stoplists/glasgow.txt"));
            //tokens = new StopFilter(tokens, loadStopList("stoplists/smart.txt"));

            //tokens = new PorterStemFilter(tokens);
            tokens = new KStemFilter(tokens);

            //tokens = new NGramTokenFilter(tokens, 3);

        }

        if(fieldName.equals("SHINGLE")){
            tokens = new KStemFilter(tokens);
            tokens = new StopFilter(tokens, loadStopList("stoplists/glasgow.txt"));

            ShingleFilter shinglefilter = new ShingleFilter(tokens, 3);
            shinglefilter.setFillerToken("differentFillerForSentencesAndQuery");
            tokens = shinglefilter;

        }


        return new TokenStreamComponents(source, tokens);
    }

    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        // return new HTMLStripCharFilter(reader);
        return super.initReader(fieldName, reader);
    }

    @Override
    protected TokenStream normalize(String fieldName, TokenStream in) {
        return new LowerCaseFilter(in);
    }

    /**
     * Main method of the class.
     *
     * @param args command line arguments.
     *
     * @throws IOException if something goes wrong while processing the text.
     */
    public static void main(String[] args) throws IOException {
        // text to analyze
        final String text = "I now live in Rome where I met my wife Alice back in 2010 during a beautiful afternoon. " +
                "Occasionally, I fly to New York to visit the United Nations where I would like to work. The last " +
                "time I was there in March 2019, the flight was very inconvenient, leaving at 4:00 am, and expensive," +
                " over 1,500 dollars.";
        //final String text = "For the first time, in its 800 academic year, University of Padua has a female " +
        //		"Rector.";

        // use the analyzer to process the text and print diagnostic information about each token
        consumeTokenStream(new ToucheAnalyzer(), text);
    }
}