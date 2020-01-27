package slr203tp.messages;

import java.io.Serializable;
import akka.actor.ActorRef;

public class RespondToActor implements Serializable {

    private final ActorRef sender;
    private final ActorRef finalReceiver;
    private final Response message;

    public RespondToActor(ActorRef sender, ActorRef actor, Response message) {
        this.sender = sender;
        this.finalReceiver = actor;
        this.message = org.apache.commons.lang3.SerializationUtils.clone(message);
    }

    public ActorRef getSender() {
        return sender;
    }

    public ActorRef getFinalReceiver() {
        return finalReceiver;
    }

    public Response getMessage() {
        return message;
    }
}