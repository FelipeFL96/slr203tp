package slr203tp;

import java.util.ArrayList;
import akka.actor.Props;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Start;
import slr203tp.messages.Message;
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
        if (message instanceof Start) {
            actors.add(getContext().getSystem().actorOf(SampleActor.createActor(), "actor1"));
            actors.add(getContext().getSystem().actorOf(SampleActor.createActor(), "actor2"));
            actors.add(getContext().getSystem().actorOf(SampleActor.createActor(), "actor3"));
            actors.add(getContext().getSystem().actorOf(SampleActor.createActor(), "actor4"));

            for (int i = 0; i < 4; i++) {
                ActorsList list = new ActorsList();
                for (int j = 0; j < 4; j++) {
                    if (actorLinks[i][j] == 1) {
                        list.addActor(actors.get(j));
                    }
                }
                actors.get(i).tell(list, getSelf());
            }
        }
    }

}