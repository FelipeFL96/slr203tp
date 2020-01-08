package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;

public class Multicast {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef multicaster = system.actorOf(Multicaster.createActor(), "multicaster");
        final ActorRef receiver1 = system.actorOf(Receiver.createActor(), "receiver1");
        final ActorRef receiver2 = system.actorOf(Receiver.createActor(), "receiver2");
        final ActorRef receiver3 = system.actorOf(Receiver.createActor(), "receiver3");
        final ActorRef sender = system.actorOf(Sender.createActor(multicaster, receiver1, receiver2, receiver3), "sender");

        sender.tell(new Start(), ActorRef.noSender());

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