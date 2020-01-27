package slr203tp.messages;

import java.io.Serializable;
import akka.actor.ActorRef;

public class SessionCreated implements Serializable {

    private ActorRef session;

    public SessionCreated(ActorRef session) {
        this.session = session;
    }

    public ActorRef getSession() {
        return session;
    }

}