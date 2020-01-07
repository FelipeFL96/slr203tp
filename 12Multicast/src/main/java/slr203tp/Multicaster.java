package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.RegisterGroup;
import slr203tp.messages.MulticastTransmission;

public class Multicaster extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ArrayList<Group> receiverGroups;

    public Multicaster() {
        receiverGroups = new ArrayList<Group>();        
    }

    public static Props createActor() {
        return Props.create(Multicaster.class, () -> {
            return new Multicaster();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof RegisterGroup) {
            RegisterGroup received = (RegisterGroup) message;
            receiverGroups.add(received.getGroup());
            
            String receiversString = "";
            ArrayList<ActorRef> receivers = received.getGroup().getMembers();
            for (ActorRef receiver : receivers) {
                if (receivers.indexOf(receiver) == receivers.size() - 1) {
                    receiversString = receiversString + " and ";
                }
                else if (!(receivers.indexOf(receiver) == 0)) {
                    receiversString = receiversString + ", ";
                }
                receiversString = receiversString + receiver.path().name();
            }
            log.info("[" + getSelf().path().name() + "] new group registered by " + getSender().path().name() + ": " + receiversString);
        }
        else if (message instanceof MulticastTransmission) {
            MulticastTransmission received = (MulticastTransmission) message;
            int index = receiverGroups.indexOf(received.getReceiverGroup());
            for (ActorRef receiver : receiverGroups.get(index).getMembers()) {
                receiver.tell(received.getMessage(), getSender());
            }
        }
    }

}