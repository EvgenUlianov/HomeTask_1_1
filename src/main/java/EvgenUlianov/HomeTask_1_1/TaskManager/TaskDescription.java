package EvgenUlianov.HomeTask_1_1.TaskManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskDescription {
    //Encapsulation
    private String name;

    private boolean completed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public TaskDescription(String name) {
        this.name = name;
        this.completed = false;
    }

    public void toggle() {
        completed = !(completed);
    }

}
