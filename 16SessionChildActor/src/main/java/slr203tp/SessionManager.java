package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.CreateSession;
import slr203tp.messages.SessionCreated;
import slr203tp.messages.EndSession;
import slr203tp.messages.Stop;

public class SessionManager extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SessionManager() {}

    public static Props createActor() {
        return Props.create(SessionManager.class, () -> {
            return new SessionManager();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof CreateSession) {
            ActorRef session = getContext().getSystem().actorOf(Session.createActor(), "session1");
            getSender().tell(new SessionCreated(session), getSelf());
        }
        else if (message instanceof EndSession) {
            EndSession received = (EndSession) message;
            received.getSession().tell(new Stop(), getSelf());
        }
    }    

}