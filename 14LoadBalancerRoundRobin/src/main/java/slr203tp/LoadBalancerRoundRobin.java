package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;

public class LoadBalancerRoundRobin {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef loadbalancer = system.actorOf(LoadBalancer.createActor(), "loadbalancer");
        final ActorRef a = system.actorOf(FirstActor.createActor(loadbalancer), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(loadbalancer), "b");
        final ActorRef c = system.actorOf(ThirdActor.createActor(loadbalancer), "c");

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