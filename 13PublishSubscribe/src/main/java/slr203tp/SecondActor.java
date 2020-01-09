package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Subscribe;
import slr203tp.messages.Message;

public class SecondActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef topic1, topic2;

    public SecondActor(ActorRef topic1, ActorRef topic2) {
        this.topic1 = topic1;
        this.topic2 = topic2;
    }

    public static Props createActor(ActorRef topic1, ActorRef topic2) {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor(topic1, topic2);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            topic1.tell(new Subscribe(), getSelf());
            topic2.tell(new Subscribe(), getSelf());
        }
        else if (message instanceof Message) {
            Message received = (Message) message;
            log.info("[" + getSelf().path().name() + "] message received from " + getSender().path().name() + ": " + received.getMessage());
        }
    }

}