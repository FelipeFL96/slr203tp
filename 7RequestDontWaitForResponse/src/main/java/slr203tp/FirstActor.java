package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.ResponseOne;
import slr203tp.messages.ResponseTwo;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    public FirstActor() {}

    public static Props createActor() {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof ResponseOne) {
            ResponseOne response = (ResponseOne) message;
            log.info("[" + getSelf().path().name() + "] Received Response One: " + response.getMessage());
        }
        else if (message instanceof ResponseTwo) {
            ResponseTwo response = (ResponseTwo) message;
            log.info("[" + getSelf().path().name() + "] Received Response Two: " + response.getMessage());
        }
    }
}