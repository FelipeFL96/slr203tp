package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.RequestOne;
import slr203tp.messages.RequestTwo;
import slr203tp.messages.ResponseOne;
import slr203tp.messages.ResponseTwo;

public class SecondActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public SecondActor() {}

    public static Props createActor() {
        return Props.create(SecondActor.class, () -> {
            return new SecondActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof RequestOne) {
            RequestOne request = (RequestOne) message;
            getSender().tell(new ResponseOne("Hello, I'm B!"), getSelf());
            try { // L'acteur B va prends du temps à répondre pour permettre de voir que A ne lui attendra pas
                waitBeforeResponding(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("[" + getSelf().path().name() + "] Received Request One: " + request.getMessage());
        }
        else if (message instanceof RequestTwo) {
            RequestTwo request = (RequestTwo) message;
            log.info("[" + getSelf().path().name() + "] Received RequestTwo: " + request.getMessage());
            getSender().tell(new ResponseTwo("I'm fine, thanks!"), getSelf());
        }
    }

    private void waitBeforeResponding(int waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }
}