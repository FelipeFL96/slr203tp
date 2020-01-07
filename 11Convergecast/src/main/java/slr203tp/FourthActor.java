package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Hi;
import slr203tp.messages.Hi2;
import java.util.ArrayList;

public class FourthActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public FourthActor() {}

    public static Props createActor() {
        return Props.create(FourthActor.class, () -> {
            return new FourthActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Hi) {
            Hi received = (Hi) message;
            ArrayList<ActorRef> senders = received.getSenders();
            String sendersString = "";
            for (ActorRef sender : senders) {
                if (senders.indexOf(sender) == senders.size() - 1) {
                    sendersString = sendersString + " and ";
                }
                else if (senders.indexOf(sender) != 0) {
                    sendersString = sendersString + ", ";
                }
                sendersString = sendersString + sender.path().name();
            }
            log.info("[" + getSelf().path().name() + "] received from " + sendersString + ": "  + received.getMessage());
        }
        else if (message instanceof Hi2) {
            Hi2 received = (Hi2) message;
            ArrayList<ActorRef> senders = received.getSenders();
            String sendersString = "";
            for (ActorRef sender : senders) {
                if (senders.indexOf(sender) == senders.size() - 1) {
                    sendersString = sendersString + " and ";
                }
                else if (senders.indexOf(sender) != 0) {
                    sendersString = sendersString + ", ";
                }
                sendersString = sendersString + sender.path().name();
            }
            log.info("[" + getSelf().path().name() + "] received from " + sendersString + ": "  + received.getMessage());
        }
    }
}