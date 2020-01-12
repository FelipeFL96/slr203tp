package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Stop;
import slr203tp.messages.Message;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    final ActorRef check;

    public FirstActor(ActorRef check) {
        this.check = check;
    }

    public static Props createActor(ActorRef check) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(check);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            check.tell(new Start(), getSelf());
        }
        else if (message instanceof Message) {
            Message m = (Message) message;
            log.info("[" + getSelf().path().name() + "] received: " + m.getMessage());
        }
        else if (message instanceof Stop) {
            getContext().stop(getSelf());
            log.info("[" + getSelf().path().name() + "] Stopped");
        }
    }

    public static void wait(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }

}