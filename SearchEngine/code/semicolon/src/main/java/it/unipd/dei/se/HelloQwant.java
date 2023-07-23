package it.unipd.dei.se;

import it.unipd.dei.se.analyze.CustomAnalzer;
import it.unipd.dei.se.index.BodyField;
import it.unipd.dei.se.parse.ParsedDocument;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.FSDirectory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


public class HelloQwant {

    public static void main(String[] args) throws Exception {

        /**
         * The total number of indexed documents.
         */
        final long[] docsCount = {0};
        final long expectedDocs = 1570734;

        /**
         * The total number of indexed files.
         */
        final long[] filesCount = {0};
        /**
         * The total number of indexed bytes
         */
        final long[] bytesCount = {0};
        final int MBYTE = 1024 * 1024;
        final IndexWriter writer;
        final Path indexDir = Paths.get("code/semicolon/indexed");

//        final Analyzer analyzer = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class).addTokenFilter(
//                LowerCaseFilterFactory.class).addTokenFilter(StopFilterFactory.class).build();
        final Analyzer analyzer = new CustomAnalzer(
                "code/semicolon/src/main/resources/Synonyms",
                "code/semicolon/src/main/resources/stopwords.txt");


//                final Analyzer a  = CustomAnalyzer.builder()
//                .withTokenizer(StandardTokenizerFactory.class)
//                .addTokenFilter(LowerCaseFilterFactory.class)
//                .addTokenFilter(ApostropheFilterFactory.class)
//                .addTokenFilter(EnglishPossessiveFilterFactory.class)
//                .addTokenFilter(WordDelimiterGraphFilterFactory.class)
//                .addTokenFilter(StopFilterFactory.class, "words", "stopwords.txt", "format", "snowball")
//                .addTokenFilter(SynonymGraphFilterFactory.class, "synonyms", "Synonyms")
//                .build();
//        final Analyzer a = new HamidehAnalyzer().createComponents("nlp");

        final IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        final Similarity similarity = new BM25Similarity(1.3F, 0.75F);

        iwc.setSimilarity(similarity);
        final int ramBufferSizeMB = 256;
        iwc.setRAMBufferSizeMB(ramBufferSizeMB);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        iwc.setCommitOnClose(true);
        iwc.setUseCompoundFile(true);


        try {
            writer = new IndexWriter(FSDirectory.open(indexDir), iwc);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("Unable to create the index writer in directory %s: %s.",
                    indexDir.toAbsolutePath().toString(), e.getMessage()), e);
        }

        final Path docsDir = Path.of("code/ProcessedOutput");

        long start = System.currentTimeMillis();
        Files.walkFileTree(docsDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().endsWith(".json")) {
                    bytesCount[0] += Files.size(file);

                    filesCount[0] += 1;

                    // Creates a FileReader
                    FileReader fileReader = new FileReader(file.toString());
                    // Creates a BufferedReader
                    BufferedReader buffer = new BufferedReader(fileReader);

                    String line;
                    line = buffer.readLine();

                    JSONArray docsArray;
                    try {
                        docsArray = new JSONArray(
                                line
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    String id = null;
                    Document doc;
                    for (int i = 0; i < docsArray.length(); i++) {
                        ParsedDocument document;
                        if (!docsArray.isNull(i)) {
                            JSONObject contentsItem;
                            try {
                                contentsItem = docsArray.getJSONObject(i);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (contentsItem.has("id")) {
                                try {
                                    id = contentsItem.getString("id");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            if (contentsItem.has("contents")) {
                                final StringBuilder body = new StringBuilder();
                                try {
                                    body.append(contentsItem.getString("contents"));
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                document = new ParsedDocument(id, body.length() > 0 ? body.toString() : "#");

                                doc = new Document();

                                // add the document identifier
                                doc.add(new StringField(ParsedDocument.FIELDS.ID, document.getIdentifier(), Field.Store.YES));

                                doc.add(new BodyField(document.getBody()));

                                writer.addDocument(doc);
                                docsCount[0]++;

                                // print progress every 10000 indexed documents
                                if (docsCount[0] % 10000 == 0) {
                                    System.out.printf("%d document(s) (%d files, %d Mbytes) indexed in %d seconds.%n",
                                            docsCount[0], filesCount[0], bytesCount[0] / MBYTE,
                                            (System.currentTimeMillis() - start) / 1000);
                                }
                            }
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        writer.commit();

        writer.close();

        if (docsCount[0] != expectedDocs) {
            System.out.printf("Expected to index %d documents; %d indexed instead.%n", expectedDocs, docsCount[0]);
        }

        System.out.printf("%d document(s) (%d files, %d Mbytes) indexed in %d seconds.%n", docsCount[0], filesCount[0],
                bytesCount[0] / MBYTE, (System.currentTimeMillis() - start) / 1000);

        System.out.printf("#### Indexing complete ####%n");

    }


}


