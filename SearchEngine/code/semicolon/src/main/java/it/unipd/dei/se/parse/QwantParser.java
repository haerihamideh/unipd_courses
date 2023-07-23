/*
 *  Copyright 2017-2022 University of Padua, Italy
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package it.unipd.dei.se.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Provides a very basic parser for the TIPSTER corpus (FBIS, FR, FT, LATIMES), i.e. TREC Disks 4 and 5 minus
 * Congressional Record.
 * <p>
 * It is based on the parser <a href="https://github.com/isoboroff/trec-demo/blob/master/src/TrecDocIterator.java"
 * target="_blank">TrecDocIterator.java</a> by Ian Soboroff.
 *
 * @author Nicola Ferro
 * @version 1.00
 * @since 1.00
 */
public class QwantParser extends it.unipd.dei.se.parse.DocumentParser {

    /**
     * The size of the buffer for the body element.
     */
    private static final int BODY_SIZE = 1024 * 8;

    /**
     * The currently parsed document
     */
    private it.unipd.dei.se.parse.ParsedDocument document = null;


    /**
     * Creates a new TIPSTER Corpus document parser.
     *
     * @param in the reader to the document(s) to be parsed.
     * @throws NullPointerException     if {@code in} is {@code null}.
     * @throws IllegalArgumentException if any error occurs while creating the parser.
     */
    public QwantParser(final Reader in) {
        super(new BufferedReader(in));
    }


    @Override
    public boolean hasNext() {

        String id = null;
        final StringBuilder body = new StringBuilder(BODY_SIZE);

        try {
            String line;
            line = ((BufferedReader) in).readLine();

            if (line == null) {
                next = false;
                return next;
            }
            JSONObject jo = new JSONObject(
                    line
            );

            id = String.valueOf(jo.getString("id"));

            body.append(jo.getString("contents"));

        } catch (IOException | JSONException e) {
            throw new IllegalStateException("Unable to parse the document.", e);
        }

        if (id != null) {
            document = new ParsedDocument(id, body.length() > 0 ?
                    body.toString() : "#");
        }
        return next;
    }

    @Override
    protected final it.unipd.dei.se.parse.ParsedDocument parse() {
        return document;
    }


    /**
     * Main method of the class. Just for testing purposes.
     *
     * @param args command line arguments.
     * @throws Exception if something goes wrong while indexing.
     */
    public static void main(String[] args) throws Exception {

        Reader reader = new FileReader(
                "code/ProcessedOutput");

        QwantParser p = new QwantParser(reader);

        for (it.unipd.dei.se.parse.ParsedDocument d : p) {
            System.out.printf("%n%n------------------------------------%n%s%n%n%n", d.toString());
        }


    }

}
