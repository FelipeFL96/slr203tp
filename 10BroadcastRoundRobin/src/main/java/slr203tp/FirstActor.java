package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Message;
import slr203tp.messages.Start;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private ActorRef broadcaster;

    public FirstActor(ActorRef broadcaster) {
        this.broadcaster = broadcaster;
    }

    public static Props createActor(ActorRef broadcaster) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(broadcaster);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            try {
                wait(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }   
            log.info("[" + getSelf().path().name() + "] Sending Message to the broadcasting group");
            broadcaster.tell(new Message("Hello, everybody!"), getSelf());
        }
    }

    private void wait(int timeMillis) throws InterruptedException {
		Thread.sleep(timeMillis);
    }
}