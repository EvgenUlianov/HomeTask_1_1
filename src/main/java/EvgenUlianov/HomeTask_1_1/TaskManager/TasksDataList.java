package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;


//Abstraction: we are going to change "List" to Database

@Slf4j
@Service
public class TasksDataList implements TasksData {

    private final static int OUT_OF_INDEX = -1;

    @Autowired
    private IOStream ioStream;

    {
        tasks = new ArrayList<>();
    }

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

    @Deprecated
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
    public List<TaskDescriptionWeb> getTasksWeb(Predicate<TaskDescription> predicate) {
        List<TaskDescriptionWeb> listWeb = new ArrayList<>();
        IntStream.range(0, tasks.size())
                .filter((index) -> {
                    TaskDescription task = tasks.get(index);
                    return predicate.test(task);
                })
                .forEach((index) -> {listWeb.add(new TaskDescriptionWeb(index + 1, tasks.get(index)));});

        return listWeb;
    }

    @Deprecated
    @Override
    public void printTask(int key) {
        TaskDescription task = tasks.get(key);
        ioStream.println(String.format("%d. [%s] %s", key + 1, (task.isCompleted() ? "x" : " "), task.getName()));
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
        ioStream.println(msg);
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

}
