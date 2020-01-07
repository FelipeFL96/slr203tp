package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Response;

public class ThirdActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public ThirdActor() {}

    public static Props createActor() {
        return Props.create(ThirdActor.class, () -> {
            return new ThirdActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Response) {
            Response response = (Response) message;
            log.info("[" + getSelf().path().name() + "] received from " + getSender().path().name() + ": " + response.getMessage());
        }
    }
}