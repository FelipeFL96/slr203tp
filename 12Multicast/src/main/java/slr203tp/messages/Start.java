package slr203tp.messages;

import akka.actor.ActorRef;

public class Start {

    private ActorRef receiver1, receiver2, receiver3;

    public Start(ActorRef receiver1, ActorRef receiver2, ActorRef receiver3) {
        this.receiver1 = receiver1;
        this.receiver2 = receiver2;
        this.receiver3 = receiver3;
    }
    

    public ActorRef getReceiver1() {
        return receiver1;
    }

    public ActorRef getReceiver2() {
        return receiver2;
    }

    public ActorRef getReceiver3() {
        return receiver3;
    }

}