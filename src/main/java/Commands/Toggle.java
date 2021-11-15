package Commands;

import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Toggle extends  Command{
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
        Integer number = super.getNumber(stringNumber);
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        if (number == null) return;
        if (number <= 0 || number > tasks.size()) {
            super.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
            return;
        }

        TaskDescription taskDescription;
        taskDescription = tasks.get(number - 1);
        taskDescription.toggle();
    }
}
