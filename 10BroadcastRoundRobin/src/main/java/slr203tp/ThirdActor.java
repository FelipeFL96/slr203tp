package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Message;
import slr203tp.messages.Join;
import slr203tp.messages.Start;

public class ThirdActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef broadcaster;

    public ThirdActor(ActorRef broadcaster) {
        this.broadcaster = broadcaster;
    }

    public static Props createActor(ActorRef broadcaster) {
        return Props.create(ThirdActor.class, () -> {
            return new ThirdActor(broadcaster);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            broadcaster.tell(new Join(), getSelf());
        }
        else if (message instanceof Message) {
            Message received = (Message) message;
            log.info("[" + getSelf().path().name() + "] received from " + getSender().path().name() + ": "  + received.getMessage());
        }
    }
}