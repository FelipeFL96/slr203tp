package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Join;
import slr203tp.messages.Message;

public class Broadcaster extends UntypedAbstractActor {

    private ArrayList<ActorRef> broadcastJoiners;
    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public Broadcaster() {
        broadcastJoiners = new ArrayList<ActorRef>();
    }

    public static Props createActor() {
        return Props.create(Broadcaster.class, () -> {
            return new Broadcaster();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Join) {
            broadcastJoiners.add(getSender());
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " joined the broadcast group");
        }
        else if (message instanceof Message) {
            Message m = (Message) message;
            for (ActorRef actor : broadcastJoiners) {
                actor.tell(m, getSender());
            }
        }
    }

}