package slr203tp.messages;

import java.io.Serializable;

public class Hello implements Serializable {

    private final String message = "Hello";

    public Hello() {}

    public String getMessage() {
        return message;
    }
    
}