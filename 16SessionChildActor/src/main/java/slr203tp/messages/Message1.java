package slr203tp.messages;

import java.io.Serializable;

public class Message1 implements Serializable {

    private final String message;

    public Message1(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}