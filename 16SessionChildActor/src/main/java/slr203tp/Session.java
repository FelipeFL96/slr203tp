package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Stop;
import slr203tp.messages.Message1;
import slr203tp.messages.Message2;
import slr203tp.messages.Message3;

public class Session extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Session() {
        log.info("[" + getSelf().path().name() + "] Session created");
    }

    public static Props createActor() {
        return Props.create(Session.class, () -> {
            return new Session();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Stop) {
            getContext().stop(getSelf());
            log.info("[" + getSelf().path().name() + "] Stopped");
        }
        else if (message instanceof Message1) {
            Message1 received = (Message1) message;
            log.info("[" + getSelf().path().name() + "] got message from " + getSender().path().name() + ": " + received.getMessage());
            getSender().tell(new Message2("Tasks in progress... concluded!"), getSelf());
        }
        else if (message instanceof Message3) {
            Message3 received = (Message3) message;
            log.info("[" + getSelf().path().name() + "] got message from " + getSender().path().name() + ": " + received.getMessage());
        }
    }   

}