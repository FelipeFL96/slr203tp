package slr203tp.messages;

import java.io.Serializable;

public class Max implements Serializable {

    private final int max;
    
    public Max(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

}