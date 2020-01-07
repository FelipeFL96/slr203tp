package slr203tp;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import slr203tp.messages.StartMessage;

public class RequestBlockWaitForResponse_ASK {

    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("system");

        final ActorRef a = system.actorOf(FirstActor.createActor(), "a");
        final ActorRef b = system.actorOf(SecondActor.createActor(), "b");

        //b.tell(new RequestOne("Hello, I'm A!"), a);
        //b.tell(new RequestTwo("How are you?"), a);
        a.tell(new StartMessage(b), ActorRef.noSender());

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