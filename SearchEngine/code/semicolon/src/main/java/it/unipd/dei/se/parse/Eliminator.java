package it.unipd.dei.se.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;

public class Eliminator {
    public static String removeDiacritics(String text) {
        String normalizedString = Normalizer.normalize(text, Normalizer.Form.NFD);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < normalizedString.length(); i++) {
            char c = normalizedString.charAt(i);
            if (Character.getType(c) != Character.NON_SPACING_MARK) {
                stringBuilder.append(c);
            }
        }

        return Normalizer.normalize(stringBuilder.toString(), Normalizer.Form.NFC);
    }

    public static void sanitization(String inputFolder, String outputFolder, boolean writeInOneLine) {

        // Create Jackson object mapper
        ObjectMapper mapper = new ObjectMapper();

        // Get all files in the input folder
        File[] inputFiles = new File(inputFolder).listFiles((dir, name) -> name.endsWith(".json"));

        long start = System.currentTimeMillis();
        long fileCount = 0;

        System.out.printf("#### sanitizing  ####%n");
        // Process each input file
        for (File inputFile : inputFiles) {
            try {
                fileCount++;
                // Read JSON data from input file and parse it into a JsonNode
                String inputFilename = inputFile.getName();
                String jsonInput = FileUtils.readFileToString(inputFile, "UTF-8");
                JsonNode rootNode = mapper.readTree(jsonInput);
                // create output file
                String outputFilename = inputFilename.replace(".json", "_processed.json");
                File outputFile = new File(outputFolder, outputFilename);
                try (FileWriter writer = new FileWriter(outputFile)) {

                    // Process contents attribute of each object in the array
                    for (JsonNode node : rootNode) {
                        String contents = removeDiacritics(node.get("contents").asText());

                        String strippedContents = removeDiacritics(contents);
                        ((ObjectNode) node).put("contents", strippedContents);
                        // Remove diacritical marks
                        contents = removeDiacritics(contents);

                        // Define a Lucene analyzer with accent folding
                        Analyzer analyzer = new Analyzer() {
                            @Override
                            protected TokenStreamComponents createComponents(String fieldName) {
                                final StandardTokenizer tokenizer = new StandardTokenizer();
                                tokenizer.setMaxTokenLength(255);
                                TokenStream tokenStream = new ASCIIFoldingFilter(tokenizer);
                                return new TokenStreamComponents(tokenizer, tokenStream);
                            }
                        };
                        TokenStream tokenStream = analyzer.tokenStream("contents", new StringReader(contents));

                        // Get the filtered text from the token stream
                        StringBuilder filteredText = new StringBuilder();
                        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                        tokenStream.reset();
                        while (tokenStream.incrementToken()) {
                            filteredText.append(charTermAttribute.toString()).append(" ");
                        }
                        filteredText.setLength(filteredText.length()); // Remove the extra space at the end

                        // Update the contents attribute with the filtered text
                        ((ObjectNode) node).put("contents", filteredText.toString());

                        if (!writeInOneLine) {
                            // Write the updated object to output file on a single line
                            String jsonOutput = mapper.writeValueAsString(node);
                            writer.write(jsonOutput);
                            writer.write("\n"); // Write a newline character to separate each object
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error writing to output file: " + e.getMessage());
                }

                if (writeInOneLine) {
                    // Write updated JSON data to output file
                    String jsonOutput = mapper.writeValueAsString(rootNode);
                    FileUtils.writeStringToFile(outputFile, jsonOutput, "UTF-8");
                }

                System.out.println("Processed " + inputFilename + ", wrote to " + outputFilename);
                System.out.printf("%d files sanitized in %d seconds.%n", fileCount, (System.currentTimeMillis() - start) / 1000);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("%d files sanitized in %d seconds.%n", fileCount, (System.currentTimeMillis() - start) / 1000);
        System.out.printf("#### sanitizing completed ####%n");
    }
    public static void main(String[] args) {
        // Configuration options
        String language = "French";
        // if write in one line or separately
        boolean writeInOneLine = false;

        String inputFolder = "code/OriginalData/"+language+"/Documents/Json"; // Change to the path of input folder
//        String inputFolder = "OriginalData/English/Documents/Json"; // Change to the path of input folder

        String suffix = !writeInOneLine ? "/Separated" : "/OneLine";
        String outputFolder = "code/ProcessedOutput/"+language + suffix;// Change to the path of output folder

        sanitization(inputFolder, outputFolder, writeInOneLine);
    }
}