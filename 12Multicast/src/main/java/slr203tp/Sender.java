package slr203tp;

import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.RegisterGroup;
import slr203tp.messages.MulticastTransmission;
import slr203tp.messages.Hello;
import slr203tp.messages.World;

public class Sender extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ActorRef multicaster, receiver1, receiver2, receiver3;

    public Sender(ActorRef multicaster, ActorRef receiver1, ActorRef receiver2, ActorRef receiver3) {
        this.multicaster = multicaster;
        this.receiver1 = receiver1;
        this.receiver2 = receiver2;
        this.receiver3 = receiver3;
    }

    public static Props createActor(ActorRef multicaster, ActorRef receiver1, ActorRef receiver2, ActorRef receiver3) {
        return Props.create(Sender.class, () -> {
            return new Sender(multicaster, receiver1, receiver2, receiver3);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Start) {
            Group group1 = new Group();
            group1.addMember(receiver1);
            group1.addMember(receiver2);
            multicaster.tell(new RegisterGroup(group1), getSelf());

            Group group2 = new Group();
            group2.addMember(receiver2);
            group2.addMember(receiver3);
            multicaster.tell(new RegisterGroup(group2), getSelf());

            multicaster.tell(new MulticastTransmission(new Hello(), group1), getSelf());
            multicaster.tell(new MulticastTransmission(new World(), group2), getSelf());
        }
    }

}