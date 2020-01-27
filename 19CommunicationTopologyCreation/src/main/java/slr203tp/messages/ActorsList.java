package slr203tp.messages;

import java.io.Serializable;
import java.util.ArrayList;
import akka.actor.ActorRef;

public class ActorsList implements Serializable {

    private final ArrayList<ActorRef> actorsList;

    public ActorsList() {
        this.actorsList = new ArrayList<ActorRef>();
    }
    
    public ActorsList(ArrayList<ActorRef> actorsList) {
        this.actorsList = actorsList;
    }

    public ArrayList<ActorRef> getActorsList() {
        return this.actorsList;
    }

    public void addActor(ActorRef actor) {
        this.actorsList.add(actor);
    }
}