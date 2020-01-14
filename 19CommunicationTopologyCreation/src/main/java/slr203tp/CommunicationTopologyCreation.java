package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.Start;

public class CommunicationTopologyCreation {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final int[][] actorLinks = {
            {0, 1, 1, 0},
            {0, 0, 0, 1}, 
            {1, 0, 0, 1}, 
            {1, 0, 0, 1}
        };

        final ActorRef supervisor = system.actorOf(Supervisor.createActor(actorLinks), "sup");

        supervisor.tell(new Start(), ActorRef.noSender());

        try {
            waitBeforeTerminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }

    public static void waitBeforeTerminate() throws InterruptedException {
		Thread.sleep(5000);
    }


}