package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import slr203tp.messages.Start;
import slr203tp.messages.MyMessage;
import slr203tp.messages.TransmissionMessage;

public class FirstActor extends UntypedAbstractActor {

    private ActorRef transmitter;
    private ActorRef b;

    public FirstActor(ActorRef transmitter, ActorRef b) {
        this.transmitter = transmitter;
        this.b = b;
    }

    public static Props createActor(ActorRef transmitter, ActorRef b) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(transmitter, b);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            transmitter.tell(new TransmissionMessage(new MyMessage("Olá!"), b), getSelf());
        }
    }
}