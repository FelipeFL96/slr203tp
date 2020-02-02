package slr203tp.messages;

import java.io.Serializable;

public class Broadcast implements Serializable {

    private final String message;

    public Broadcast(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}