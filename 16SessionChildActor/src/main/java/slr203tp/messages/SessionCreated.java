package slr203tp.messages;

import akka.actor.ActorRef;

public class SessionCreated {

    private ActorRef session;

    public SessionCreated(ActorRef session) {
        this.session = session;
    }

    public ActorRef getSession() {
        return session;
    }

}