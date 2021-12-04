package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Add implements Command{

    final private TasksDataList tasks;
    final private NameController nameController;

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
