package slr203tp.messages;

import slr203tp.Group;

public class MulticastTransmission {

    private Object message;
    private Group receiverGroup;
    
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