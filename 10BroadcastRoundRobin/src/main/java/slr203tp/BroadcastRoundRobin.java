package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;

public class BroadcastRoundRobin {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef broadcaster = system.actorOf(Broadcaster.createActor(), "bc");
        final ActorRef a = system.actorOf(FirstActor.createActor(broadcaster), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(broadcaster), "b");
        final ActorRef c = system.actorOf(ThirdActor.createActor(broadcaster), "c");

        b.tell(new Start(), ActorRef.noSender());
        c.tell(new Start(), ActorRef.noSender());
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