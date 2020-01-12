package slr203tp;

import java.util.ArrayList;
import akka.actor.ActorRef;
import akka.actor.UntypedAbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import slr203tp.messages.Max;
import slr203tp.messages.Task;
import slr203tp.messages.FinishedTask;
import slr203tp.messages.Message;
import slr203tp.messages.Stop;

public class LoadBalancer extends UntypedAbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    
    private ArrayList<ActorRef> workers;
    private ArrayList<Integer> tasksPerWorker;
    private int lastSent = -1;
    private int maxWorkers;

    public LoadBalancer() {
        workers = new ArrayList<ActorRef>();
        tasksPerWorker = new ArrayList<Integer>();
    }

    public static Props createActor() {
        return Props.create(LoadBalancer.class, () -> {
            return new LoadBalancer();
        });
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof Max) {
            Max received = (Max) message;
            maxWorkers = received.getMax();
            log.info("[" + getSelf().path().name() + "] max workers defined to " + maxWorkers);
        }
        else if (message instanceof Task) {
            log.info("[" + getSelf().path().name() + "] New task received");
            
            if (workers.size() < maxWorkers) {
                ActorRef worker = getContext().getSystem().actorOf(Worker.createActor(), "w" + Integer.toString(workers.size() + 1));
                workers.add(worker);
                worker.tell(message, getSelf());
                lastSent = workers.indexOf(worker);
                tasksPerWorker.add(lastSent, new Integer(1));
            }
            else {
                if (lastSent == workers.size() - 1) {
                    workers.get(0).tell(message, getSelf());
                    lastSent = 0;
                    tasksPerWorker.set(lastSent, tasksPerWorker.get(lastSent) + 1);
                }
                else {
                    workers.get(++lastSent).tell(message, getSelf());
                    tasksPerWorker.set(lastSent, tasksPerWorker.get(lastSent) + 1);
                }
            }
        }
        else if (message instanceof FinishedTask) {
            log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " finished a task");
            if (tasksPerWorker.get(workers.indexOf(getSender())).equals(1)) {
                getSender().tell(new Stop(), getSelf());
            }
            else {
                tasksPerWorker.set(workers.indexOf(getSender()), tasksPerWorker.get(workers.indexOf(getSender())) - 1);
                log.info("[" + getSelf().path().name() + "] " + getSender().path().name() + " has " + tasksPerWorker.get(workers.indexOf(getSender())) + " tasks left");
            }
        }
    }

}