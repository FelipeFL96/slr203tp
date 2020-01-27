package slr203tp;

import java.io.Serializable;
import java.util.ArrayList;
import akka.actor.ActorRef;

public class Group implements Serializable {

    private ArrayList<ActorRef> members;

    public Group() {
        members = new ArrayList<ActorRef>();
    }
    
    public Group(ArrayList<ActorRef> members) {
        this.members = members;
    }

    public ArrayList<ActorRef> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<ActorRef> members) {
        this.members = members;
    }

    public void addMember(ActorRef member) {
        members.add(member);
    }

}