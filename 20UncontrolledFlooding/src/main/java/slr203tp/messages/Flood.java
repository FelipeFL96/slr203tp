package slr203tp.messages;

import java.io.Serializable;

public class Flood implements Serializable {

    private final String message;

    public Flood(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}