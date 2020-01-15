package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.Topology;

public class UncontrolledFloodingWithoutCycle {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final int[][] actorLinks = {
            {0, 1, 1, 0, 0},
            {0, 0, 0, 1, 0}, 
            {0, 0, 0, 1, 0}, 
            {0, 0, 0, 0, 1},
            {0, 0, 1, 0, 0}
        };

        final ActorRef supervisor = system.actorOf(Supervisor.createActor(actorLinks), "sup");

        supervisor.tell(new Topology(actorLinks), ActorRef.noSender());

    }

}