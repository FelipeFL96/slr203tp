package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Join;
import slr203tp.messages.Hi;
import slr203tp.messages.Hi2;

public class SecondActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef merger;

    public SecondActor(ActorRef merger) {
        this.merger = merger;
    }

    public static Props createActor(ActorRef merger) {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor(merger);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            merger.tell(new Join(), getSelf());
            wait(200);
            merger.tell(new Hi(), getSelf());
            wait(300);
            merger.tell(new Hi2(), getSelf());
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