package slr203tp.messages;

import java.io.Serializable;

public class Task implements Serializable {

    private final String task;
    
    public Task(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }
    
}