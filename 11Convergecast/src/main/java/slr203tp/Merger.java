package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Join;
import slr203tp.messages.Unjoin;
import slr203tp.messages.Hi;
import slr203tp.messages.Hi2;

public class Merger extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef destination;
    private ArrayList<ActorRef> mergeJoiners;
    private ArrayList<ActorRef> HiSenders;
    private ArrayList<ActorRef> Hi2Senders;

    public Merger(ActorRef destination) {
        mergeJoiners = new ArrayList<ActorRef>();
        HiSenders = new ArrayList<ActorRef>();
        Hi2Senders = new ArrayList<ActorRef>();
        this.destination = destination;
        log.info("[" + getSelf().path().name() + "] Merger created! Destination actor: " + destination.path().name());
    }

    public static Props createActor(ActorRef destination) {
        return Props.create(Merger.class, () -> {
            return new Merger(destination);
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Join) {
            mergeJoiners.add(getSender());
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " joined the merge group");
        }
        else if (message instanceof Unjoin) {
            if (mergeJoiners.remove(getSender())) {
                log.info("[" + getSelf().path().name()+ "] " + getSender().path().name() + " unjoined the merge group");
            }
            else {
                log.info("[" + getSelf().path().name()+ "] Failed to unjoin " + getSender().path().name() + " from the group");
            }
        }
        else if (message instanceof Hi) {
            HiSenders.add(getSender());
            if (ableToSend(HiSenders)) {
                destination.tell(new Hi(HiSenders), ActorRef.noSender());
            }
        }
        else if (message instanceof Hi2) {
            Hi2Senders.add(getSender());
            if (ableToSend(Hi2Senders)) {
                destination.tell(new Hi2(Hi2Senders), ActorRef.noSender());
            }
        }
    }

    private Boolean ableToSend(ArrayList<ActorRef> senders) {
        for (ActorRef joiner : mergeJoiners) {
            if (!senders.contains(joiner)) {
                return false;
            }
        }
        return true;
    }

}