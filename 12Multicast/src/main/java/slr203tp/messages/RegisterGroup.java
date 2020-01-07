package slr203tp.messages;

import slr203tp.Group;

public class RegisterGroup {

    private Group group;

    public RegisterGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

}