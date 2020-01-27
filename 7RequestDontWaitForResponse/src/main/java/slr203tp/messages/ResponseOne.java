package slr203tp.messages;

import java.io.Serializable;

public class ResponseOne implements Serializable {
    
    private final String message;

    public ResponseOne(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}