package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Hello;
import slr203tp.messages.World;

public class Receiver extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Receiver() {}

    public static Props createActor() {
        return Props.create(Receiver.class, () -> {
            return new Receiver();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Hello) {
            Hello received = (Hello) message;
            log.info("[] received from " + getSender().path().name() + ": " + received.getMessage());
        }
        else if (message instanceof World) {
            World received = (World) message;
            log.info("[] received from " + getSender().path().name() + ": " + received.getMessage());
        }
    }

}