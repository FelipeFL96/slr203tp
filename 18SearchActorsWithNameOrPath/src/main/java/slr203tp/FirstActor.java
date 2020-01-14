package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Create;
import slr203tp.messages.GetPath;
import slr203tp.messages.ReturnPath;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private int X = 1;

    public FirstActor() {}

    public static Props createActor() {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Create) {
            getContext().getSystem().actorOf(SampleActor.createActor(), "actor" + Integer.toString(X++));
        }
        else if (message instanceof GetPath) {
            getSender().tell(new ReturnPath(), getSelf());
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