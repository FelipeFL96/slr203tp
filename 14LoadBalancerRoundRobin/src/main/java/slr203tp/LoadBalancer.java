package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Join;
import slr203tp.messages.Unjoin;
import slr203tp.messages.Message;

public class LoadBalancer extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ArrayList<ActorRef> joiners;
    private int lastSent = -1;

    public LoadBalancer() {
        joiners = new ArrayList<ActorRef>();
    }

    public static Props createActor() {
        return Props.create(LoadBalancer.class, () -> {
            return new LoadBalancer();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Join) {
            joiners.add(getSender());
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " joined " + getSelf().path().name());
        }
        else if (message instanceof Unjoin) {
            joiners.remove(getSender());
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " unjoined " + getSelf().path().name());
        }
        else if (message instanceof Message) {
            if (joiners.size() == 0) {
                log.info("[" + getSelf().path().name() + "] No joiners to distribute the messages.");
            }
            else if (lastSent == -1) {
                joiners.get(0).tell(message, getSender());
                lastSent = 0;
            }
            else if (lastSent >= joiners.size() - 1) {
                joiners.get(0).tell(message, getSender());
                lastSent = 0;
            }
            else {
                joiners.get(++lastSent).tell(message, getSender());
            }
        }
    }

}