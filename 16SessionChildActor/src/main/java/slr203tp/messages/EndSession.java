package slr203tp.messages;

import akka.actor.ActorRef;

public class EndSession {

    ActorRef session;

    public EndSession(ActorRef session) {
        this.session = session;
    }

    public ActorRef getSession() {
        return session;
    }

}