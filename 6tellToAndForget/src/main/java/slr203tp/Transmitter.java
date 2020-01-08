package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import slr203tp.messages.TransmissionMessage;
import slr203tp.messages.MyMessage;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Transmitter extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Transmitter() {}

    public static Props createActor() {
        return Props.create(Transmitter.class, () -> {
            return new Transmitter();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof TransmissionMessage) {
            log.info("[" + getSelf().path().name() + "] Message being Transmitted");
            log.info("[" + getSelf().path().name() + "] Source: " + getSender().path().name());
            TransmissionMessage transMessage = (TransmissionMessage) message;
            log.info("[" + getSelf().path().name() + "] Destination: " + transMessage.getReceiver().path().name());
            transMessage.getReceiver().tell(transMessage.getMessage(), getSender());
        }
    }

}