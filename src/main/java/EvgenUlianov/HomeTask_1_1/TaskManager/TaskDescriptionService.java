package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.repositories.TaskDescriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskDescriptionService {

    final private TaskDescriptionRepository taskRepository;

    public Iterable<TaskDescription> tasks(User user){
//        return taskRepository.findAll(Sort.by("Id"));
        if (user == null)
            return new ArrayList<>();
        return taskRepository.findOwn(user.getId());
    }

    public TaskDescription getTask(Integer id, User user){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findOwnById(longId, user.getId());
        return  entity.get();
    }

    public TaskDescription toggle(Integer id, User user){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findOwnById(longId, user.getId());
        if (entity != null){
            entity.get().toggle();
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    public TaskDescription edit(Integer id, String name, User user){
        if (checkName(name))
            return null;
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findOwnById(longId, user.getId());

        if (entity != null){
            entity.get().setName(name);
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    public void delete(Integer id, User user){
        long longId = (long) id;
        taskRepository.deleteOwnById(longId, user.getId());
    }

    public TaskDescription add(String name, User user){
        TaskDescription taskDescription = new TaskDescription(name);
        if (checkName(name))
            return null;
        taskDescription.setOwner(user);
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
