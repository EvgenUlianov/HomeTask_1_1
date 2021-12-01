package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.General.WordDelimiter;
import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Edit implements Command{

    @Autowired
    private TasksDataList tasks;

    @Autowired
    private NameController nameController;

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getDescription() {
        return "edit <идентификатор задачи> <новое значение>";
    }

    @Override
    public void accept(String argumentCommandWord) {
        WordDelimiter wordDelimiter = new WordDelimiter(argumentCommandWord);
        String name = wordDelimiter.getSecondWord();
        if (nameController.checkName(name)) return;

        String stringNumber = wordDelimiter.getFirstWord();

        TaskDescription taskDescription = tasks.get(stringNumber);
        if (taskDescription != null)
            taskDescription.setName(name);
    }
}
