package slr203tp.messages;

import java.io.Serializable;
import akka.actor.ActorRef;

public class TransmissionMessage implements Serializable {

    public final Object message;
    public final ActorRef receiver;

    public TransmissionMessage(Object message, ActorRef actorRef) {
        this.message = message;
        this.receiver = actorRef;
    }

    public Object getMessage() {
        return message;
    }

    public ActorRef getReceiver() {
        return receiver;
    }
}