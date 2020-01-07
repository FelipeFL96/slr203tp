package slr203tp.messages;

import akka.actor.ActorRef;

public class Start {

    private ActorRef actor;

    public Start(ActorRef actor) {
        this.actor = actor;
    }

    public ActorRef getActor() {
        return actor;
    }
}