package slr203tp.messages;

public class Flood {

    private String message;
    public Integer seq;

    public Flood(String message, int seq) {
        this.message = message;
        this.seq = new Integer(seq);
    }

    public String getMessage() {
        return message;
    }
}