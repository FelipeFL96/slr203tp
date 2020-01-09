package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Join;
import slr203tp.messages.Unjoin;
import slr203tp.messages.Message;

public class ThirdActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef loadbalancer;

    public ThirdActor(ActorRef loadbalancer) {
        this.loadbalancer = loadbalancer;
    }

    public static Props createActor(ActorRef loadbalancer) {
        return Props.create(ThirdActor.class, () -> {
            return new ThirdActor(loadbalancer);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            wait(100);
            loadbalancer.tell(new Join(), getSelf());
        }
        else if (message instanceof Message) {
            Message received = (Message) message;
            log.info("[" + getSelf().path().name() + "] message received from " + getSender().path().name() + ": " + received.getMessage());
            wait(400);
            loadbalancer.tell(new Unjoin(), getSelf());
        }
    }

    public static void wait(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}