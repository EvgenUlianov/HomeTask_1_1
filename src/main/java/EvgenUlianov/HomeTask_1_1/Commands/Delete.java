package EvgenUlianov.HomeTask_1_1.Commands;

import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Delete implements Command{

    final private TasksDataList tasks;

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
        tasks.remove(stringNumber);
    }
}
