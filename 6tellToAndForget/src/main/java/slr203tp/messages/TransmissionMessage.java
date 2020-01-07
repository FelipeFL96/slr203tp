package slr203tp.messages;

import java.io.Serializable;
import akka.actor.ActorRef;

public class TransmissionMessage implements Serializable {

    public final String message;
    public ActorRef receiver;

    public TransmissionMessage(String message, ActorRef actorRef) {
        this.message = message;
        this.receiver = actorRef;
    }

    public String getMessage() {
        return message;
    }

    public ActorRef getReceiver() {
        return receiver;
    }
}