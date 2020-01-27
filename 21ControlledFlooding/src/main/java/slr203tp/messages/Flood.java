package slr203tp.messages;

import java.io.Serializable;

public class Flood implements Serializable {

    private final String message;
    public final Integer seq;

    public Flood(String message, int seq) {
        this.message = message;
        this.seq = org.apache.commons.lang3.SerializationUtils.clone(new Integer(seq));
    }

    public String getMessage() {
        return message;
    }
}