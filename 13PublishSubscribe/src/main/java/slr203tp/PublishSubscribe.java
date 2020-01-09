package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;

public class PublishSubscribe {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef topic1 = system.actorOf(Topic.createActor(), "topic1");
        final ActorRef topic2 = system.actorOf(Topic.createActor(), "topic2");
        final ActorRef a = system.actorOf(FirstActor.createActor(topic1), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(topic1, topic2), "b");
        final ActorRef c = system.actorOf(ThirdActor.createActor(topic2), "c");
        final ActorRef publisher1 = system.actorOf(FirstPublisher.createActor(topic1), "publisher1");
        final ActorRef publisher2 = system.actorOf(SecondPublisher.createActor(topic2), "publisher2");

        a.tell(new Start(), ActorRef.noSender());
        b.tell(new Start(), ActorRef.noSender());
        c.tell(new Start(), ActorRef.noSender());
        publisher1.tell(new Start(), ActorRef.noSender());
        publisher2.tell(new Start(), ActorRef.noSender());

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