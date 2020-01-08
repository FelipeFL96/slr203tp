package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;

public class TellToAndForget {

  public static void main(String[] args) {

    final ActorSystem system = ActorSystem.create("system");

    final ActorRef transmitter = system.actorOf(Transmitter.createActor(), "transmitter");
    final ActorRef a = system.actorOf(FirstActor.createActor(transmitter), "a");
    final ActorRef b = system.actorOf(SecondActor.createActor(), "b");

    a.tell(new Start(b), ActorRef.noSender());

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