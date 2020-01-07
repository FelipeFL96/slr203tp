package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.RespondToActor;
import slr203tp.messages.Response;

public class RespondTo {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef a = system.actorOf(FirstActor.createActor(), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(), "b");
        final ActorRef c = system.actorOf(ThirdActor.createActor(), "c");

        b.tell(new RespondToActor(a, c, new Response ("Hello! I'm A and I'm too shy to talk to you directly!")), a);

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