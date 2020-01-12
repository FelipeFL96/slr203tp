package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.PoisonPill;
import akka.actor.Kill;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Stop;
import slr203tp.messages.Message;
import java.time.Duration;
import akka.pattern.AskTimeoutException;
import static akka.pattern.Patterns.gracefulStop;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class Terminator extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ActorRef a, b, c, d;

    public Terminator(ActorRef a, ActorRef b, ActorRef c, ActorRef d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static Props createActor(ActorRef a, ActorRef b, ActorRef c, ActorRef d) {
        return Props.create(Terminator.class, () -> {
            return new Terminator(a, b, c, d);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            
            log.info("[" + getSelf().path().name() + "] Starting " + a.path().name());
            a.tell(new Message("Good Morning!"), getSelf());
            wait(500);
            log.info("[" + getSelf().path().name() + "] Stopping " + a.path().name());
            a.tell(new Stop(), getSelf());
            a.tell(new Message("Good Morning!"), getSelf());
            wait(500);

            System.out.println("");

            log.info("[" + getSelf().path().name() + "] Starting " + b.path().name());
            b.tell(new Message("Good Afternoon!"), getSelf());
            wait(500);
            log.info("[" + getSelf().path().name() + "] Stopping " + b.path().name());
            b.tell(PoisonPill.getInstance(), getSelf());
            b.tell(new Message("Good Afternoon!"), getSelf());
            wait(500);

            System.out.println("");

            log.info("[" + getSelf().path().name() + "] Starting " + c.path().name());
            c.tell(new Message("Good Evening!"), getSelf());
            wait(500);
            log.info("[" + getSelf().path().name() + "] Stopping " + c.path().name());
            c.tell(Kill.getInstance(), getSelf());
            c.tell(new Message("Good Evening!"), getSelf());
            wait(500);

            System.out.println("");

            log.info("[" + getSelf().path().name() + "] Starting " + d.path().name());
            d.tell(new Message("Good Night!"), getSelf());
            wait(500);
            log.info("[" + getSelf().path().name() + "] Stopping " + d.path().name());
            try {
                CompletionStage<Boolean> stopped = 
                    gracefulStop(d, Duration.ofSeconds(5), PoisonPill.getInstance());
                stopped.toCompletableFuture().get(6, TimeUnit.SECONDS);
            }
            catch (AskTimeoutException e) {
                e.printStackTrace();
            }
            d.tell(new Message("Good Night!"), getSelf());
            wait(500);


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