package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Toggle implements  Command{

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
        TasksData tasks = TasksDataList.get();
        TaskDescription taskDescription = tasks.get(stringNumber);
        if (taskDescription != null)
            taskDescription.toggle();
    }
}
