package Commands;

import General.WordDelimiter;
import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Edit extends Command{
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
        if (super.checkName(name)) return;

        String stringNumber = wordDelimiter.getFirstWord();
        Integer number = super.getNumber(stringNumber);
        if (number == null) return;
        //Abstraction: we are going to change "List" to Database
        TasksData tasks = TasksDataList.get();
        if (number <= 0 || number > tasks.size()) {
            super.printAndLog(String.format("идентификатор %d вне границ массива задач", number));
            return;
        }

        TaskDescription taskDescription;
        taskDescription = tasks.get(number - 1);
        taskDescription.setName(name);
    }
}
