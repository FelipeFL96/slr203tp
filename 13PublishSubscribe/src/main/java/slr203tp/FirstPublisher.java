package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Message;

public class FirstPublisher extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef topic1;

    public FirstPublisher(ActorRef topic1) {
        this.topic1 = topic1;
    }

    public static Props createActor(ActorRef topic1) {
        return Props.create(FirstPublisher.class, () -> {
            return new FirstPublisher(topic1);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            wait(200);
            topic1.tell(new Message("Hello!"), getSelf());
            wait(200);
            topic1.tell(new Message("Hello again!"), getSelf());
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