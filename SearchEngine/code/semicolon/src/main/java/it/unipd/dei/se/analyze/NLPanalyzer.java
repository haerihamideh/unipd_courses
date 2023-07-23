package it.unipd.dei.se.analyze;


import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.opennlp.OpenNLPLemmatizerFilter;
import org.apache.lucene.analysis.opennlp.OpenNLPPOSFilter;
import org.apache.lucene.analysis.opennlp.OpenNLPTokenizer;
import org.apache.lucene.analysis.opennlp.tools.*;
import org.apache.lucene.analysis.tokenattributes.*;
import org.apache.lucene.document.Document;
import opennlp.tools.lemmatizer.LemmatizerModel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;


public class NLPanalyzer extends Analyzer {

    /**
     * The class loader of this class. Needed for reading files from the {@code resource} directory.
     */
    private static final ClassLoader CL = Analyzer.class.getClassLoader();

    /**
     * Creates a new instance of the analyzer.
     */
    public NLPanalyzer() {
        super();
    }

    /**
     * Here we put the tokenizer and all the token filters we want to implement
     */
    @Override
    protected TokenStreamComponents createComponents(String s) {

        //Tokenizer


        try {
            Tokenizer  source = new OpenNLPTokenizer(TokenStream.DEFAULT_TOKEN_ATTRIBUTE_FACTORY,
                    loadSentenceDetectorModel("en-sent.bin"), loadTokenizerModel("en-token.bin"));

            //Filters

            TokenStream tokens = new OpenNLPPOSFilter(source, loadPosTaggerModel("en-pos-maxent.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-location.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-person.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-organization.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-money.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-date.bin"));

            tokens = new OpenNLPNERFilter(tokens, loadLNerTaggerModel("en-ner-time.bin"));

            tokens = new OpenNLPLemmatizerFilter(tokens, loadLemmatizerModel("en-lemmatizer.bin"));


            return new TokenStreamComponents(source, tokens);


        } catch (IOException e) {
            // The OpenNLPTokenizer seems to have a "wrong" signature declaring to throw an IOException which actually
            // is never thrown. This forces us to wrap everything with try-catch.

            throw new IllegalStateException(
                    String.format("Unable to create the OpenNLPTokenizer: %s. This should never happen: surprised :-o",
                            e.getMessage()), e);
        }
    }


    public static void consumeTokenStream(Analyzer analyzer, final Document doc) throws IOException {

        // the start time of the processing
        final long start = System.currentTimeMillis();

        // Create a new TokenStream for a dummy field
        final TokenStream stream = analyzer.tokenStream("field", new StringReader(doc.toString()));

        // Lucene tokens are decorated with different attributes whose values contain information about the token,
        // e.g. the term represented by the token, the offset of the token, etc.

        // The term represented by the token
        final CharTermAttribute tokenTerm = stream.addAttribute(CharTermAttribute.class);

        // The type the token
        final TypeAttribute tokenType = stream.addAttribute(TypeAttribute.class);

        // Whether the token is a keyword. Keyword-aware TokenStreams/-Filters skip modification of tokens that are keywords
        final KeywordAttribute tokenKeyword = stream.addAttribute(KeywordAttribute.class);

        // The position of the token wrt the previous token
        final PositionIncrementAttribute tokenPositionIncrement = stream.addAttribute(PositionIncrementAttribute.class);

        // The number of positions occupied by a token
        final PositionLengthAttribute tokenPositionLength = stream.addAttribute(PositionLengthAttribute.class);

        // The start and end offset of a token in characters
        final OffsetAttribute tokenOffset = stream.addAttribute(OffsetAttribute.class);

        // Optional flags a token can have
        final FlagsAttribute tokenFlags = stream.addAttribute(FlagsAttribute.class);

//        stream.close();
        System.out.printf("####################################################################################%n");
        System.out.printf("Text to be processed%n");
        System.out.printf("+ %s%n%n", doc);

        System.out.printf("Tokens%n");
        try {
            // Reset the stream before starting
            stream.reset();

            // Print all tokens until the stream is exhausted
            while (stream.incrementToken()) {
                System.out.printf("+ token: %s%n", tokenTerm.toString());
                System.out.printf("  - type: %s%n", tokenType.type());
                System.out.printf("  - keyword: %b%n", tokenKeyword.isKeyword());
                System.out.printf("  - position increment: %d%n", tokenPositionIncrement.getPositionIncrement());
                System.out.printf("  - position length: %d%n", tokenPositionLength.getPositionLength());
                System.out.printf("  - offset: [%d, %d]%n", tokenOffset.startOffset(), tokenOffset.endOffset());
                System.out.printf("  - flags: %d%n", tokenFlags.getFlags());
            }

            // Perform any end-of-stream operations
            stream.end();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // Close the stream and release all the resources
            stream.close();
        }

        System.out.printf("%nElapsed time%n");
        System.out.printf("+ %d milliseconds%n", System.currentTimeMillis() - start);
        System.out.printf("####################################################################################%n");

    }



    /**
     * Loads the required Apache OpenNLP sentence detector model among those available in the {@code resources} folder.
     *
     * @param modelFile the name of the file containing the model.
     *
     * @return the required Apache OpenNLP model.
     *
     * @throws IllegalStateException if there is any issue while loading the model.
     */
    static NLPSentenceDetectorOp loadSentenceDetectorModel(final String modelFile) {

        if (modelFile == null) {
            throw new NullPointerException("Model file name cannot be null.");
        }

        if (modelFile.isEmpty()) {
            throw new IllegalArgumentException("Model file name cannot be empty.");
        }

        // the model
        NLPSentenceDetectorOp model = null;

        try {

            // Get an input stream for the file containing the model
            InputStream in = new BufferedInputStream(CL.getResourceAsStream(modelFile));

            // Load the model
            model = new NLPSentenceDetectorOp(new SentenceModel(in));

            // Close the file
            in.close();

        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load the model %s: %s", modelFile, e.getMessage()),
                    e);
        }

        return model;
    }

    static NLPLemmatizerOp loadLemmatizerModel(final String modelFile) {

        if (modelFile == null) {
            throw new NullPointerException("Model file name cannot be null.");
        }

        if (modelFile.isEmpty()) {
            throw new IllegalArgumentException("Model file name cannot be empty.");
        }

        // the model
        NLPLemmatizerOp model = null;

        try {

            // Get an input stream for the file containing the model
            InputStream in = new BufferedInputStream(CL.getResourceAsStream(modelFile));

            // Load the model
            model = new NLPLemmatizerOp(null, new LemmatizerModel(in));

            // Close the file
            in.close();

        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load the model %s: %s", modelFile, e.getMessage()),
                    e);
        }

        return model;
    }


    /**
     * Loads the required Apache OpenNLP tokenizer model among those available in the {@code resources} folder.
     *
     * @param modelFile the name of the file containing the model.
     *
     * @return the required Apache OpenNLP model.
     *
     * @throws IllegalStateException if there is any issue while loading the model.
     */
    static NLPTokenizerOp loadTokenizerModel(final String modelFile) {

        if (modelFile == null) {
            throw new NullPointerException("Model file name cannot be null.");
        }

        if (modelFile.isEmpty()) {
            throw new IllegalArgumentException("Model file name cannot be empty.");
        }

        // the model
        NLPTokenizerOp model = null;

        try {

            // Get an input stream for the file containing the model
            InputStream in = new BufferedInputStream(CL.getResourceAsStream(modelFile));

            // Load the model
            model = new NLPTokenizerOp(new TokenizerModel(in));

            // Close the file
            in.close();

        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load the model %s: %s", modelFile, e.getMessage()),
                    e);
        }

        return model;
    }


    /**
     * Loads the required Apache OpenNLP POS tagger model among those available in the {@code resources} folder.
     *
     * @param modelFile the name of the file containing the model.
     *
     * @return the required Apache OpenNLP model.
     *
     * @throws IllegalStateException if there is any issue while loading the model.
     */
    static NLPPOSTaggerOp loadPosTaggerModel(final String modelFile) {

        if (modelFile == null) {
            throw new NullPointerException("Model file name cannot be null.");
        }

        if (modelFile.isEmpty()) {
            throw new IllegalArgumentException("Model file name cannot be empty.");
        }

        // the model
        NLPPOSTaggerOp model = null;

        try {

            // Get an input stream for the file containing the model
            InputStream in = new BufferedInputStream(CL.getResourceAsStream(modelFile));

            // Load the model
            model = new NLPPOSTaggerOp(new POSModel(in));

            // Close the file
            in.close();

        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load the model %s: %s", modelFile, e.getMessage()),
                    e);
        }

        return model;
    }



    /**
     * Loads the required Apache OpenNLP NER tagger model among those available in the {@code resources} folder.
     *
     * @param modelFile the name of the file containing the model.
     *
     * @return the required Apache OpenNLP model.
     *
     * @throws IllegalStateException if there is any issue while loading the model.
     */
    static NLPNERTaggerOp loadLNerTaggerModel(final String modelFile) {

        if (modelFile == null) {
            throw new NullPointerException("Model file name cannot be null.");
        }

        if (modelFile.isEmpty()) {
            throw new IllegalArgumentException("Model file name cannot be empty.");
        }

        // the model
        NLPNERTaggerOp model = null;

        try {

            // Get an input stream for the file containing the model
            InputStream in = new BufferedInputStream(CL.getResourceAsStream(modelFile));

            // Load the model
            model = new NLPNERTaggerOp(new TokenNameFinderModel(in));

            // Close the file
            in.close();

        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to load the model %s: %s", modelFile, e.getMessage()),
                    e);
        }

        return model;
    }

}
