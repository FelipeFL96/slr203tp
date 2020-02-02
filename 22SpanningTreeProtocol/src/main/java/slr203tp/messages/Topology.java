package slr203tp.messages;

import java.io.Serializable;

public class Topology implements Serializable {

    private final int[][] topology;
    public final int numberOfActors;

    public Topology(int[][] topology) {
        this.topology = org.apache.commons.lang3.SerializationUtils.clone(topology);
        this.numberOfActors = topology.length;
    }

    public int[][] getTopology() {
        return this.topology;
    }
    
}