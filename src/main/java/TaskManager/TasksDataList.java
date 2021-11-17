package TaskManager;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;


//Abstraction: we are going to change "List" to Database

@Slf4j
public class TasksDataList implements TasksData {

    private final static int OUT_OF_INDEX = -1;

    //Encapsulation
    private final List<TaskDescription> tasks;

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
    public void remove(String stringNumber){
        int index = getIndex(stringNumber);
        if (index != OUT_OF_INDEX)
            tasks.remove(index);
    }

    @Override
    public TaskDescription get(int index){
        return tasks.get(index);
    }

    @Override
    public TaskDescription get(String stringNumber){
        int index = getIndex(stringNumber);
        if (index != OUT_OF_INDEX)
            return tasks.get(index);
        return null;
    }

    @Override
    public void printTasks(Predicate<TaskDescription> predicate) {
        IntStream.range(0, tasks.size())
                .filter((index) -> {
                    TaskDescription task = tasks.get(index);
                    return predicate.test(task);
                })
                .forEach(this::printTask);
    }

    @Override
    public void printTask(int key) {
        TaskDescription task = tasks.get(key);
        System.out.printf("%d. [%s] %s%n", key + 1, (task.isCompleted() ? "x" : " "), task.getName());
    }

    // private service ++

    //Encapsulation
    private Integer getIndex(String stringNumber){
        Integer number = getNumber(stringNumber);
        if (number == null) return OUT_OF_INDEX;
        if (number <= 0 || number > tasks.size()) {
            printAndLog(String.format("идентификатор %d вне границ массива задач", number));
            return OUT_OF_INDEX;
        }
        return number - 1;
    }

    //Encapsulation
    private Integer getNumber(String stringNumber) {
        if (stringNumber.length() == 0) {
            printAndLog("Не указан номер");
            return null;
        }

        int number;
        try {
            number = Integer.parseInt(stringNumber);
        } catch (NumberFormatException ex) {
            log.error(ex.getMessage(), ex);
            ex.printStackTrace();
            return null;
        }
        return number;
    }

    //Encapsulation
    private void printAndLog(String msg){
        System.out.println(msg);
        log.error(msg);
        /*
        может-быть пригодится когда-нибудь
        if (Main.isLoggingEnabled()) {

            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(msg);
            if (trace.length > 2) {
                stringBuilder.append("\n");
                IntStream.range(2, trace.length)
                        .forEach((index) -> {
                            String traceText = trace[index].toString();
                            stringBuilder.append("\t\t");
                            stringBuilder.append(traceText);
                            stringBuilder.append("\n");
                        });
            }
        }*/
    }

    // private service --

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
