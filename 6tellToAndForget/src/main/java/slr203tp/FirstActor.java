package slr203tp;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

public class FirstActor extends UntypedAbstractActor {

    public FirstActor() {}

    public static Props createActor() {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor();
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }
}