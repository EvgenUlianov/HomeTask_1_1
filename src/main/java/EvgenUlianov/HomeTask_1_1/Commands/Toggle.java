package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Toggle implements  Command{

    final private TasksDataList tasks;

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
        TaskDescription taskDescription = tasks.get(stringNumber);
        if (taskDescription != null)
            taskDescription.toggle();
    }
}
