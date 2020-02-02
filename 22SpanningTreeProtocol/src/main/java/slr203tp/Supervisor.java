package slr203tp;

import java.util.ArrayList;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Root;
import slr203tp.messages.Topology;
import slr203tp.messages.Broadcast;
import slr203tp.messages.ActorsList;

public class Supervisor extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    private ArrayList<ActorRef> actors;
    private int[][] actorLinks;
    
    public Supervisor(int[][] actorLinks) {
        actors = new ArrayList<ActorRef>();
        this.actorLinks = actorLinks;
    }

    public static Props createActor(int[][] actorLinks) {
        return Props.create(Supervisor.class, () -> {
            return new Supervisor(actorLinks);
        });
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Topology) {
            Topology top = (Topology) message;

            log.info("[" + getSelf().path().name() + "] Creating actors");
            for (int i = 0; i < top.numberOfActors; i++) {
                actors.add(getContext().actorOf(SampleActor.createActor(), "actor" + (char) ((int)'A' + i)));
            }

            log.info("[" + getSelf().path().name() + "] Setting topology");
            for (int i = 0; i < top.numberOfActors; i++) {
                ActorsList list = new ActorsList();
                for (int j = 0; j < top.numberOfActors; j++) {
                    if (actorLinks[i][j] == 1) {
                        list.addActor(actors.get(j));
                    }
                }
                actors.get(i).tell(list, getSelf());
                wait(100);
            }
            
            System.out.println("\n");   
            log.info("[" + getSelf().path().name() + "] Defining spanning tree");
            actors.get(0).tell(new Root(), getSender());

            wait(1000);

            System.out.println("\n");
            log.info("[" + getSelf().path().name() + "] Attempting broadcast");
            actors.get(0).tell(new Broadcast("Hello, actors!"), getSelf());
        }
    }

    public void wait(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}