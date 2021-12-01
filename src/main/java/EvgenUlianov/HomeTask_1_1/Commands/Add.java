package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Add implements Command{

    @Autowired
    private TasksDataList tasks;

    @Autowired
    private NameController nameController;

    @Autowired
    private IOStream ioStream;

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add <описание задачи>";
    }

    @Override
    public void accept(String name) {
        if (nameController.checkName(name)) return;
        TaskDescription taskDescription = new TaskDescription(name);
        tasks.add(taskDescription);
    }
}
