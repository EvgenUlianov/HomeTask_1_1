package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TasksOperator {

    final private TaskRepository taskRepository;

    public Iterable<TaskDescription> tasks(){
        return taskRepository.findAll(Sort.by("Id"));
    }

    public TaskDescription getTask(Integer id){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        return  entity.get();
    }

    public TaskDescription toggle(Integer id){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        if (entity != null){
            entity.get().toggle();
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    public TaskDescription edit(Integer id, String name){
        if (checkName(name))
            return null;
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);

        if (entity != null){
            entity.get().setName(name);
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    public void delete(Integer id){
        long longId = (long) id;
        taskRepository.deleteById(longId);
    }

    public TaskDescription add(String name){
        TaskDescription taskDescription = new TaskDescription(name);
        if (checkName(name))
            return null;
        taskRepository.save(taskDescription);
        return taskDescription;
    }

    private boolean checkName (String name) {
        if (name.length() == 0) {
            String msg = "Не указано наименование";
            log.error(msg);
            return true;
        }
        return false;
    }

}
