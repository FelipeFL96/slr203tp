package slr203tp.messages;

import akka.actor.ActorRef;

public class Start {

    ActorRef receiver;

    public Start(ActorRef receiver) {
        this.receiver = receiver;
    }

    public ActorRef getReceiver() {
        return receiver;
    }
    
}