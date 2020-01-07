package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.TransmissionMessage;
import slr203tp.messages.MyMessage;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class TellToAndForget {

  public static void main(String[] args) {

    final ActorSystem system = ActorSystem.create("system");

    final ActorRef a = system.actorOf(FirstActor.createActor(), "a");
    final ActorRef b = system.actorOf(SecondActor.createActor(), "b");
    final ActorRef transmitter = system.actorOf(Transmitter.createActor(), "transmitter");

    TransmissionMessage message = new TransmissionMessage("Olá!", b);
    transmitter.tell(message, a);

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