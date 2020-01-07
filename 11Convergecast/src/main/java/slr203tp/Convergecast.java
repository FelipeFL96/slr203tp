package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;

public class Convergecast {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef d = system.actorOf(FourthActor.createActor(), "d");
        final ActorRef merger = system.actorOf(Merger.createActor(d), "merger");
        final ActorRef a = system.actorOf(FirstActor.createActor(merger), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(merger), "b");
        final ActorRef c = system.actorOf(ThirdActor.createActor(merger), "c");

        a.tell(new Start(), ActorRef.noSender());
        b.tell(new Start(), ActorRef.noSender());
        c.tell(new Start(), ActorRef.noSender());

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