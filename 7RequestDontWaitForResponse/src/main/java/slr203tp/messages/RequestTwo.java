package slr203tp.messages;

import java.io.Serializable;

public class RequestTwo implements Serializable {
    
    private final String message;

    public RequestTwo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}