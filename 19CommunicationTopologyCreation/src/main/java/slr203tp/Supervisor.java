package slr203tp;

import java.util.ArrayList;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Message;
import slr203tp.messages.Topology;
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

            for (int i = 0; i < top.numberOfActors; i++) {
                actors.add(getContext().actorOf(SampleActor.createActor(), "actor" + Integer.toString(i+1)));
            }

            for (int i = 0; i < top.numberOfActors; i++) {
                ActorsList list = new ActorsList();
                for (int j = 0; j < top.numberOfActors; j++) {
                    if (actorLinks[i][j] == 1) {
                        list.addActor(actors.get(j));
                    }
                }
                actors.get(i).tell(list, getSelf());
            }
        }
    }

}