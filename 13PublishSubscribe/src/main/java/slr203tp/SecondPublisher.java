package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Message;

public class SecondPublisher extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef topic2;

    public SecondPublisher(ActorRef topic2) {
        this.topic2 = topic2;
    }

    public static Props createActor(ActorRef topic2) {
        return Props.create(SecondPublisher.class, () -> {
            return new SecondPublisher(topic2);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            wait(300);
            topic2.tell(new Message("World!"), getSelf());
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
