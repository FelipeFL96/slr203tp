package slr203tp.messages;

import akka.actor.ActorRef;

public class RespondToActor {

    private ActorRef sender;
    private ActorRef finalReceiver;
    private Response message;

    public RespondToActor(ActorRef sender, ActorRef actor, Response message) {
        this.sender = sender;
        this.finalReceiver = actor;
        this.message = message;
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