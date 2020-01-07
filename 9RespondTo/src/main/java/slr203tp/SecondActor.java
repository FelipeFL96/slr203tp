package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.RespondToActor;

public class SecondActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SecondActor() {}

    public static Props createActor() {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof RespondToActor) {
            RespondToActor respondTo = (RespondToActor) message;
            log.info("[" + getSelf().path().name() + "] received from " + getSender().path().name() + " a message from " + respondTo.getSender().path().name() + " to " + respondTo.getFinalReceiver().path().name());
            respondTo.getFinalReceiver().tell(respondTo.getMessage(), respondTo.getSender());
        }
    }
}