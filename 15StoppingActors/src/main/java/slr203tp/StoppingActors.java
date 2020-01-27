package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.Start;

public class StoppingActors {

    public static void main(String[] args) {
    final ActorSystem system = ActorSystem.create("system");

        final ActorRef a = system.actorOf(FirstActor.createActor(), "a");
        final ActorRef b = system.actorOf(FirstActor.createActor(), "b");
        final ActorRef c = system.actorOf(FirstActor.createActor(), "c");
        final ActorRef d = system.actorOf(FirstActor.createActor(), "d");
        final ActorRef terminator = system.actorOf(Terminator.createActor(a, b, c, d), "terminator");
        
        terminator.tell(new Start(), ActorRef.noSender());
        
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