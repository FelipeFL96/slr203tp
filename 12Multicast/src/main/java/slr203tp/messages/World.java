package slr203tp.messages;

import java.io.Serializable;

public class World implements Serializable {

    private final String message = "World";

    public World() {}

    public String getMessage() {
        return message;
    }
    
}