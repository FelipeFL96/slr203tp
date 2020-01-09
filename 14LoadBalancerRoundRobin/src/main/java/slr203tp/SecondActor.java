package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Join;
import slr203tp.messages.Message;

public class SecondActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef loadbalancer;

    public SecondActor(ActorRef loadbalancer) {
        this.loadbalancer = loadbalancer;
    }

    public static Props createActor(ActorRef loadbalancer) {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor(loadbalancer);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            loadbalancer.tell(new Join(), getSelf());
        }
        else if (message instanceof Message) {
            Message received = (Message) message;
            log.info("[" + getSelf().path().name() + "] message received from " + getSender().path().name() + ": " + received.getMessage());
        }
    }

}