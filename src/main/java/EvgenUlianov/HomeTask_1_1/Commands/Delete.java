package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Delete implements Command{

    @Autowired
    private TasksDataList tasks;

    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "delete <идентификатор задачи>";
    }

    @Override
    public void accept(String stringNumber) {
        tasks.remove(stringNumber);
    }
}
