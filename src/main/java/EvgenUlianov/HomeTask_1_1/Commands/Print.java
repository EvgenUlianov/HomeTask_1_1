package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Print implements Command{

    final private TasksDataList tasks;

    @Override
    public String getName() {
        return "print";
    }

    @Override
    public String getDescription() {
        return "print [all]";
    }

    @Override
    public void accept(String argument) {
        tasks.printTasks(task -> argument.equals("all") || !(task.isCompleted()));
    }
}
