package Commands;

import General.WordDelimiter;
import TaskManager.TaskDescription;
import TaskManager.TasksData;
import TaskManager.TasksDataList;

public class Edit implements Command{

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
        if (TaskDescription.checkName(name)) return;

        String stringNumber = wordDelimiter.getFirstWord();

        TasksData tasks = TasksDataList.get();
        TaskDescription taskDescription = tasks.get(stringNumber);
        if (taskDescription != null)
            taskDescription.setName(name);
    }
}
