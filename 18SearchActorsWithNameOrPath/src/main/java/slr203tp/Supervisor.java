package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Create;
import slr203tp.messages.GetPath;
import slr203tp.messages.ReturnPath;
import akka.actor.Identify;
import akka.actor.ActorIdentity;

public class Supervisor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef a;

    public Supervisor(ActorRef a) {
        this.a = a;
    }

    public static Props createActor(ActorRef a) {
        return Props.create(Supervisor.class, () -> {
            return new Supervisor(a);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            a.tell(new Create(), getSelf());
            wait(100);
            a.tell(new Create(), getSelf());
            wait(100);
            a.tell(new Create(), getSelf());
            wait(100);
            a.tell(new Create(), getSelf());
            wait(100);
            a.tell(new Create(), getSelf());
            wait(100);
            a.tell(new Create(), getSelf());
            wait(100);

            getContext().actorSelection("/user/a*").tell(new GetPath(), getSelf());
            getContext().actorSelection("/user/*").tell(new Identify(1), getSelf());
            getContext().actorSelection("/system/*").tell(new Identify(1), getSelf());
            getContext().actorSelection("/deadLetters/*").tell(new Identify(1), getSelf());
            getContext().actorSelection("/temp/*").tell(new Identify(1), getSelf());
            getContext().actorSelection("/remote/*").tell(new Identify(1), getSelf());

        }
        else if (message instanceof ReturnPath) {
            System.out.println("[akka://system/user/sup] " + getSender().path());
        }
        else if (message instanceof ActorIdentity) {
            System.out.println("[akka://system/user/sup] " + getSender().path());
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