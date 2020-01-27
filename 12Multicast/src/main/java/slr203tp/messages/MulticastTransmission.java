package slr203tp.messages;

import java.io.Serializable;
import slr203tp.Group;

public class MulticastTransmission implements Serializable {

    private final Object message;
    private final Group receiverGroup;
    
    public MulticastTransmission(Object message, Group receiverGroup) {
        this.message = message;
        this.receiverGroup = receiverGroup;
    }

    public Object getMessage() {
        return message;
    }

    public Group getReceiverGroup() {
        return receiverGroup;
    }

}