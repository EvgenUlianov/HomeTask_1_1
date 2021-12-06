package EvgenUlianov.HomeTask_1_1.TaskManager;

import java.util.List;
import java.util.function.Predicate;

//Abstraction: we are going to change "List" to Database
public interface TasksData {
    int size();

    void add(TaskDescription task);

    void remove(int index);

    void remove(String stringNumber);

    TaskDescription get(int index);

    TaskDescription get(String stringNumber);

    @Deprecated
    void printTasks(Predicate<TaskDescription> predicate);

    List<TaskDescriptionWeb> getTasksWeb(Predicate<TaskDescription> predicate);

    @Deprecated
    void printTask(int key);


}
