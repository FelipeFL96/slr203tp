package demo;

public class CreateAndeReferenceByConstruction {
    public static void main() {
        final ActorSystem system = ActorSystem.create("system");
        final ActorRef a2 = system.actorOf()


        try {
            waitBeforeTerminate() throws InterruptedException
        }
    }
}



//--------------------------------------------
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class SecondActor extends UntypedAbstractActor {
    public SecondActor() {}
    public static Props createAcotr() {
        return Props.create(SecondActor.claas, ()-> {
            return new SecondActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }
}

public static Props createActor(ActorRef)
    return Props.create() {

    }