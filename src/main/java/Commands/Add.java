package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Add implements Command{

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
        if (TaskDescription.checkName(name)) return;
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        TaskDescription taskDescription = new TaskDescription(name);
        tasks.add(taskDescription);
    }
}
