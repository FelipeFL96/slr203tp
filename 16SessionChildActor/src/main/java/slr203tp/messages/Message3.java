package slr203tp.messages;

import java.io.Serializable;

public class Message3 implements Serializable {

    private final String message;

    public Message3(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}