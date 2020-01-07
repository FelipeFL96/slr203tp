package slr203tp.messages;

import akka.actor.ActorRef;

public class StartMessage {

    private ActorRef actor;

    public StartMessage(ActorRef actor) {
        this.actor = actor;
    }

    public ActorRef getActor() {
        return actor;
    }

}