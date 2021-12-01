package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Search implements Command{

    @Autowired
    private TasksDataList tasks;

    @Autowired
    private NameController nameController;

    @Override
    public String getName() {
        return "search";
    }

    @Override
    public String getDescription() {
        return "search <substring>";
    }

    @Override
    public void accept(String substring) {
        if (nameController.checkName(substring)) return;
        tasks.printTasks(task -> task.getName().contains(substring));
    }
}
