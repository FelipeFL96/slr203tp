package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Message1;
import slr203tp.messages.Message2;
import slr203tp.messages.Message3;
import slr203tp.messages.CreateSession;
import slr203tp.messages.SessionCreated;
import slr203tp.messages.EndSession;

public class Client extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef sessionManager, session;

    public Client(ActorRef sessionManager) {
        this.sessionManager = sessionManager;
    }

    public static Props createActor(ActorRef sessionManager) {
        return Props.create(Client.class, () -> {
            return new Client(sessionManager);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            sessionManager.tell(new CreateSession(), getSelf());
        }
        else if (message instanceof SessionCreated) {
            SessionCreated received = (SessionCreated) message;
            session = received.getSession();
            session.tell(new Message1("Hello, new session! I have some tasks for you"), getSelf());
        }
        else if (message instanceof Message2) {
            Message2 received = (Message2) message;
            log.info("[" + getSelf().path().name() + "] got message from " + getSender().path().name() + ": " + received.getMessage());
            session.tell(new Message3("Tasks OK! Finishing"), getSelf());
            sessionManager.tell(new EndSession(session), getSelf());
        }
    }    

}