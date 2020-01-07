package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.RequestOne;
import slr203tp.messages.RequestTwo;
import slr203tp.messages.ResponseOne;
import slr203tp.messages.ResponseTwo;
import slr203tp.messages.StartMessage;
import static akka.pattern.Patterns.ask;
import static akka.pattern.Patterns.pipe;
import java.util.concurrent.CompletableFuture;
import java.time.Duration;
import java.xml.transform.Result;

public class FirstActor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public FirstActor() {}

    public static Props createActor() {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof StartMessage) {
            StartMessage start = (StartMessage) message;
            start.getActor().tell(new RequestOne("Hello, I'm A!"), getSelf());
            /*CompletableFuture<Object> future1 = ask(start.getActor(), new RequestOne("Hello, I'm A!"), Duration.ofMillis(1000)).toCompletableFuture();

            CompletableFuture<Result> transformed = CompletableFuture.allOf(future1).thenApply(
                v -> {
                    String x = (String) future1.join();
                    return new Result(x);
                }
            );

            pipe(transformed, system.dispatcher()).to(getSelf());*/
        }
        else if (message instanceof ResponseOne) {
            ResponseOne response = (ResponseOne) message;
            log.info("[" + getSelf().path().name() + "] Received from [" + getSender().path().name() + "]: " + response.getMessage());
            getSender().tell(new RequestTwo("How are you?"), getSelf());
        }
        else if (message instanceof ResponseTwo) {
            ResponseTwo response = (ResponseTwo) message;
            log.info("[" + getSelf().path().name() + "] Received from [" + getSender().path().name() + "]: " + response.getMessage());
        }
    }

}