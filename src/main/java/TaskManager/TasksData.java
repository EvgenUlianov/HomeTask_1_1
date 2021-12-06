package TaskManager;

import java.util.function.Predicate;

//Abstraction: we are going to change "List" to Database
public interface TasksData {
    int size();

    void add(TaskDescription task);

    void remove(int index);

    void remove(String stringNumber);

    TaskDescription get(int index);

    TaskDescription get(String stringNumber);

    void printTasks(Predicate<TaskDescription> predicate);

    void printTask(int key);


}
