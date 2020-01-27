package slr203tp.messages;

import java.io.Serializable;

public class ResponseTwo implements Serializable {
    
    private final String message;

    public ResponseTwo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}