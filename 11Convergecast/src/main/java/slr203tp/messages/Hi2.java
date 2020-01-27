package slr203tp.messages;

import java.io.Serializable;
import java.util.ArrayList;
import akka.actor.ActorRef;

public class Hi2 implements Serializable {

    private final String message = "Hi again, friend!";
    private ArrayList<ActorRef> senders;

    public Hi2() {}

    public Hi2(ArrayList<ActorRef> senders) {
        this.senders = senders;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ActorRef> getSenders() {
        return senders;
    }

    public void setSenders(ArrayList<ActorRef> senders) {
        this.senders = senders;
    }

}