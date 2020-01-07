package slr203tp.messages;

import java.io.Serializable;

public class MyMessage implements Serializable {
    private final String message;

    public MyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}