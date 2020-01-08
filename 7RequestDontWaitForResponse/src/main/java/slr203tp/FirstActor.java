package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.RequestOne;
import slr203tp.messages.RequestTwo;
import slr203tp.messages.ResponseOne;
import slr203tp.messages.ResponseTwo;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef b;
    
    public FirstActor(ActorRef b) {
        this.b = b;
    }

    public static Props createActor(ActorRef b) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(b);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            Start init = (Start) message;
            log.info("[" + getSelf().path().name() + "] Sending Request One");
            b.tell(new RequestOne("Hello, I'm A!"), getSelf());
            log.info("[" + getSelf().path().name() + "] Sending Request Two");
            b.tell(new RequestTwo("How are you?"), getSelf());
        }
        else if (message instanceof ResponseOne) {
            ResponseOne response = (ResponseOne) message;
            log.info("[" + getSelf().path().name() + "] Received Response One: " + response.getMessage());
        }
        else if (message instanceof ResponseTwo) {
            ResponseTwo response = (ResponseTwo) message;
            log.info("[" + getSelf().path().name() + "] Received Response Two: " + response.getMessage());
        }
    }
}