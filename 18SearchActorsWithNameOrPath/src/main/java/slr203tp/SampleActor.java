package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.GetPath;
import slr203tp.messages.ReturnPath;

public class SampleActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    public SampleActor() {
        log.info("[" + getSelf().path().name() + "] I exist!");
    }

    public static Props createActor() {
        return Props.create(SampleActor.class, () -> {
            return new SampleActor();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof GetPath) {
            getSender().tell(new ReturnPath(), getSelf());
        }
    }

}