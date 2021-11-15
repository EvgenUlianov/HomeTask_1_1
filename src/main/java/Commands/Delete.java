package Commands;

import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Delete extends Command{
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
        Integer number = super.getNumber(stringNumber);
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        if (number == null) return;
        if (number <= 0 || number > tasks.size()) {
            super.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
            return;
        }
        tasks.remove(number - 1);

    }
}
