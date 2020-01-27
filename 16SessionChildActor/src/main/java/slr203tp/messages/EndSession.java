package slr203tp.messages;

import java.io.Serializable;
import akka.actor.ActorRef;

public class EndSession implements Serializable {

    ActorRef session;

    public EndSession(ActorRef session) {
        this.session = session;
    }

    public ActorRef getSession() {
        return session;
    }

}