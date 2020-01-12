package slr203tp;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import slr203tp.messages.Start;
import slr203tp.messages.Max;

public class ElasticLoadBalancer {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef loadbalancer = system.actorOf(LoadBalancer.createActor(), "loadbalancer");
        final ActorRef a = system.actorOf(FirstActor.createActor(loadbalancer), "a");

        loadbalancer.tell(new Max(2), ActorRef.noSender());
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