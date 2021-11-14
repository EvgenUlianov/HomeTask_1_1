package TaskManager;

//Abstraction: we are going to change "List" to Database
public interface TasksData {
    int size();

    void add(TaskDescription task);

    void remove(int index);

    TaskDescription get(int index);

    void printTask(int key);

}
