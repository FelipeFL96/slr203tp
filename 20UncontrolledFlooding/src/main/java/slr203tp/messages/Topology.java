package slr203tp.messages;

public class Topology {

    private int[][] topology;
    public int numberOfActors;

    public Topology(int[][] topology) {
        this.topology = topology;
        this.numberOfActors = topology.length;
    }

    public int[][] getTopology() {
        return this.topology;
    }
    
}