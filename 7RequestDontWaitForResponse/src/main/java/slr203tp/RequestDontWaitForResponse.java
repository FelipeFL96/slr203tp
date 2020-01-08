package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.Start;

public class RequestDontWaitForResponse {

    public static void main(String[] args) {
        
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef b = system.actorOf(SecondActor.createActor(), "b");
        final ActorRef a = system.actorOf(FirstActor.createActor(b), "a");

        a.tell(new Start(), ActorRef.noSender());

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