package slr203tp;

import java.util.ArrayList;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Flood;
import slr203tp.messages.ActorsList;

public class SampleActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ArrayList<ActorRef> knownActors;
    
    public SampleActor() {
        this.knownActors = new ArrayList<ActorRef>();
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
                log.info("[" + getSelf().path().name() + "] actor registered: " + actor.path().name());
            }
        }
        else if (message instanceof Flood) {
            Flood received = (Flood) message;
            
            log.info("[" + getSelf().path().name() + "] Message from " + getSender().path().name() + ": " + received.getMessage());
            for (ActorRef actor : knownActors) {
                if( actor != getSender()) {
                    actor.tell(message, getSelf());
                }
            }
        }
    }

}