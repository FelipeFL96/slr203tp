package slr203tp;

import java.util.ArrayList;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Broadcast;
import slr203tp.messages.ActorsList;
import slr203tp.messages.Root;
import slr203tp.messages.Query;
import slr203tp.messages.Accept;
import slr203tp.messages.Reject;

public class SampleActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ArrayList<ActorRef> knownActors;
    private ArrayList<ActorRef> treeBranches;
    private Boolean queryAccepted = false;
    
    public SampleActor() {
        this.knownActors = new ArrayList<ActorRef>();
        this.treeBranches = new ArrayList<ActorRef>();
    }

    public static Props createActor() {
        return Props.create(SampleActor.class, () -> {
            return new SampleActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof ActorsList) {
            ActorsList received = (ActorsList) message;

            for (ActorRef actor : received.getActorsList()) {
                this.knownActors.add(actor);
                //log.info("[" + getSelf().path().name() + "] actor registered: " + actor.path().name());
            }
        }
        else if (message instanceof Root) {
            for (ActorRef actor : knownActors) {
                actor.tell(new Query(), getSelf());
            }
        }
        else if (message instanceof Query) {
            Query received = (Query) message;

            if (queryAccepted) {
                getSender().tell(new Reject(), getSelf());
            }
            else {
                getSender().tell(new Accept(), getSelf());
                queryAccepted = true;

                treeBranches.add(getSender());
                
                for (ActorRef actor : knownActors) {
                    if (actor != getSender()) {
                        actor.tell(new Query(), getSelf());
                    }
                }
            }
        }
        else if (message instanceof Accept) {
            treeBranches.add(getSender());
            log.info("[" + getSelf().path().name() + "] ACCEPTED: " + getSender().path().name());
        }
        else if (message instanceof Reject) {
            
        }
        else if (message instanceof Broadcast) {
            Broadcast bc = (Broadcast) message;
            for (ActorRef actor : treeBranches) {
                if (actor != getSender()) {
                    actor.tell(message, getSelf());
                }
            }
            log.info("[" + getSelf().path().name() + "] Received broadcast message: " + bc.getMessage());
        }
    }

}