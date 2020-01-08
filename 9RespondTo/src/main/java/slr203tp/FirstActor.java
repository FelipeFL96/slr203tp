package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Response;
import slr203tp.messages.RespondToActor;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef b, c;

    public FirstActor(ActorRef b, ActorRef c) {
        this.b = b;
        this.c = c;
    }

    public static Props createActor(ActorRef b, ActorRef c) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(b, c);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            b.tell(new RespondToActor(getSelf(), c, new Response ("Hello! I'm A and I'm too shy to talk to you directly!")), getSelf());
        }
    }

}