package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.Start;

public class SessionChildActor {

    public static void main(String[] args) {
    final ActorSystem system = ActorSystem.create("system");

        final ActorRef sessionManager = system.actorOf(SessionManager.createActor(), "sessMan");
        final ActorRef client = system.actorOf(Client.createActor(sessionManager), "client");

        client.tell(new Start(), ActorRef.noSender());
        
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