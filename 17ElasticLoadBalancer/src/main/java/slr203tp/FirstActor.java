package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Task;
import slr203tp.messages.Message;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef loadbalancer;

    public FirstActor(ActorRef loadbalancer) {
        this.loadbalancer = loadbalancer;
    }

    public static Props createActor(ActorRef loadbalancer) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(loadbalancer);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            wait(300);
            loadbalancer.tell(new Task("Buy two cakes"), getSelf());
            wait(300);
            loadbalancer.tell(new Task("Buy eight eggs"), getSelf());
            wait(300);
            loadbalancer.tell(new Task("Prepare a salade"), getSelf());
            wait(200);
        }
        else if (message instanceof Message) {
            Message received = (Message) message;
            log.info("[" + getSelf().path().name() + "] received message: " + received.getMessage());
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