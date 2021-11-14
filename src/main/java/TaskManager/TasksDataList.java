package TaskManager;

import java.util.ArrayList;
import java.util.List;


//Abstraction: we are going to change "List" to Database
public class TasksDataList implements TasksData {

    //Encapsulation
    private List<TaskDescription> tasks;

    @Override
    public int size(){
        return tasks.size();
    }

    @Override
    public void add(TaskDescription task){
        tasks.add(task);
    }

    @Override
    public void remove(int index){
        tasks.remove(index);
    }

    @Override
    public TaskDescription get(int index){
        return tasks.get(index);
    }

    @Override
    public void printTask(int key) {
        TaskDescription task = tasks.get(key);
        System.out.printf("%d. [%s] %s%n", key + 1, (task.isCompleted() ? "x" : " "), task.getName());
    }

    // SingleTone ++

    private TasksDataList(){
        tasks = new ArrayList<>();
    }

    private static class Holder {
        public static final TasksData TASKS_DATA = new TasksDataList();
    }

    public static TasksData get()  {
        return Holder.TASKS_DATA;
    }

    // SingleTone --

}
