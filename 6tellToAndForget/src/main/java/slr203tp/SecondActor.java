package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.MyMessage;

public class SecondActor extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SecondActor() {}

    public static Props createActor() {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof MyMessage) {
            MyMessage receivedMessage = (MyMessage) message;
            log.info("[" + getSelf().path().name() + "] Message received!");
            log.info("[" + getSelf().path().name() + "] Sender: " + getSender().path().name());
            log.info("[" + getSelf().path().name() + "] Content: " + receivedMessage.getMessage());
        }
    }
}