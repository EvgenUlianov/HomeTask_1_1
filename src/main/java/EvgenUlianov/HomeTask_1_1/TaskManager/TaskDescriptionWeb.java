package EvgenUlianov.HomeTask_1_1.TaskManager;

import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class TaskDescriptionWeb {

    private final int id;
    private final String description;
    private final TaskDescription taskDescription;



    public TaskDescriptionWeb(int id, TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
        this.description = String.format("[%s] %s", (taskDescription.isCompleted() ? "x" : " "), taskDescription.getName());
        this.id = id;
    }



    public String getName() {
        return taskDescription.getName();
    }

    public void setName(String name) {
        taskDescription.setName(name);
    }

    public boolean isCompleted() {
        return taskDescription.isCompleted();
    }

    public void toggle() {
        taskDescription.toggle();
    }

    public String toString() {
        return String.format("%d. [%s] %s", id, (taskDescription.isCompleted() ? "x" : " "), taskDescription.getName());
    }
}
