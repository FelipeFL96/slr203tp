package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Subscribe;
import slr203tp.messages.Message;

public class Topic extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ArrayList<ActorRef> subscribers;

    public Topic() {
        subscribers = new ArrayList<ActorRef>();
    }

    public static Props createActor() {
        return Props.create(Topic.class, () -> {
            return new Topic();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Subscribe) {
            subscribers.add(getSender());
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " subscribed to " + getSelf().path().name());
        }
        else if (message instanceof Message) {
            for (ActorRef subscriber : subscribers) {
                subscriber.tell(message, getSender());
            }
        }
    }

}