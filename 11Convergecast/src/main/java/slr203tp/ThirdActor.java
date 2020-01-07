package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Join;
import slr203tp.messages.Unjoin;
import slr203tp.messages.Hi;

public class ThirdActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef merger;

    public ThirdActor(ActorRef merger) {
        this.merger = merger;
    }

    public static Props createActor(ActorRef merger) {
        return Props.create(ThirdActor.class, () -> {
            return new ThirdActor(merger);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            merger.tell(new Join(), getSelf());
            wait(200);
            merger.tell(new Hi(), getSelf());
            wait(200);
            merger.tell(new Unjoin(), getSelf());
        }
    }

    private void wait(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}