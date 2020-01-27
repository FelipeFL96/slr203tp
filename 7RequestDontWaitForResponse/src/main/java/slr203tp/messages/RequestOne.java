package slr203tp.messages;


import java.io.Serializable;

public class RequestOne implements Serializable {
    
    private final String message;

    public RequestOne(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}