package Commands;

import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Delete implements Command{

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
        TasksData tasks = TasksDataList.get();
        tasks.remove(stringNumber);
    }
}
