package it.unipd.dei.se.parse;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.document.Field;


public class ParsedSentence implements Serializable {
    /**
     * The names of the {@link Field}s within the index for the sentences.
     * All fields of an entry.
     * @version 1.00
     * @since 1.00
     */
    public final static class FIELDS {
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String SENT_ID = "sent_id";
        public static final String SENT_TEXT = "sent_text";
    }

    /**
     * The sent_id of the sentences
     */
    private String sent_id;

    /**
     * The sent_text of the sentences
     */
    private String sent_text;

    public String getSent_id() {
        return sent_id;
    }

    public String getSent_text() {
        return sent_text;
    }

    public void setSent_id(String sent_id) {
        this.sent_id = sent_id;
    }

    public void setSent_text(String sent_text) {
        this.sent_text = sent_text;
    }

    public String toString(){
        List<Pair<String,String>> pairs = new ArrayList<>();
        pairs.add(new ImmutablePair<>(FIELDS.SENT_ID,this.sent_id));
        pairs.add(new ImmutablePair<>(FIELDS.SENT_TEXT, this.sent_text));
        return pairs.toString();
    }
}

