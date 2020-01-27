package slr203tp.messages;

import java.io.Serializable;
import slr203tp.Group;

public class RegisterGroup implements Serializable {

    private final Group group;

    public RegisterGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

}