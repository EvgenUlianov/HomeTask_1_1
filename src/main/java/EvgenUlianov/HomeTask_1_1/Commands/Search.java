package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Search implements Command{

    final private TasksDataList tasks;
    final private NameController nameController;

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
