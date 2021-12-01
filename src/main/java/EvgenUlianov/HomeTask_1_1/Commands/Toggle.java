package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Toggle implements  Command{

    @Autowired
    private TasksDataList tasks;

    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getDescription() {
        return "toggle <идентификатор задачи>";
    }

    @Override
    public void accept(String stringNumber) {
        TaskDescription taskDescription = tasks.get(stringNumber);
        if (taskDescription != null)
            taskDescription.toggle();
    }
}
