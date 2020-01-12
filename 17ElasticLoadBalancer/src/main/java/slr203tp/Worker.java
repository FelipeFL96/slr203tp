package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Stop;
import slr203tp.messages.Task;
import slr203tp.messages.FinishedTask;
import slr203tp.messages.Message;

public class Worker extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    public Worker() {
        log.info("[" + getSelf().path().name() + "] New worker created");
    }

    public static Props createActor() {
        return Props.create(Worker.class, () -> {
            return new Worker();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Stop) {
            getContext().stop(getSelf());
            log.info("[" + getSelf().path().name() + "] Stopped");
        }
        else if (message instanceof Task) {
            Task received = (Task) message;
            log.info("[" + getSelf().path().name() + "] task received from " + getSender().path().name() + ": " + received.getTask());
            wait(2000);
            getSender().tell(new FinishedTask(), getSelf());
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