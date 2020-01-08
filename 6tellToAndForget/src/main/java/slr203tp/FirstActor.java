package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import slr203tp.messages.Start;
import slr203tp.messages.MyMessage;
import slr203tp.messages.TransmissionMessage;

public class FirstActor extends UntypedAbstractActor {

    private ActorRef transmitter;

    public FirstActor(ActorRef transmitter) {
        this.transmitter = transmitter;
    }

    public static Props createActor(ActorRef transmitter) {
        return Props.create(FirstActor.class, () -> {
            return new FirstActor(transmitter);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            Start received = (Start) message;
            ActorRef b = received.getReceiver();
            transmitter.tell(new TransmissionMessage(new MyMessage("Olá!"), b), getSelf());
        }
    }
}

/* Parce que le but c'est d'avoir dix à la fin, c'est d'avoir le diplôme à la fin d'année. C'est pas d'avoir 20*/
/* Traduzindo: cinco bola é dez */