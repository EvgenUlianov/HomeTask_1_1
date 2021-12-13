package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("taskRest")
public class TaskControllerRest {
    //http://localhost:54322/

    final private NameController nameController;
    final private TaskRepository taskRepository;

    @GetMapping
    public Iterable<TaskDescription> tasks(){

        //уверен, что есть способ лучше, но я пока не изучил, как получить в Java даанные изначально в отсортированном виде
        Iterable<TaskDescription> repositoryAll = taskRepository.findAll();
        Map<Long, TaskDescription> tasks = new TreeMap<>();
        repositoryAll.forEach(taskDescription -> tasks.put(taskDescription.getId(), taskDescription));
        //tasks.values()
        return tasks.values();
    }

    @GetMapping("/{id}")
    public TaskDescription getTask(@PathVariable Integer id){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        return  entity.get();
    }

    @PatchMapping("/toggle/{id}")
    public TaskDescription toggle(@PathVariable Integer id){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        if (entity != null){
            entity.get().toggle();
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    @PatchMapping("/edit/{id}")
    public TaskDescription edit(@PathVariable Integer id,
                       @RequestParam(value = "name", required = true) String name){
        if (nameController.checkName(name))
            return null;
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);

        if (entity != null){
            entity.get().setName(name);
            taskRepository.save(entity.get());
        }
        return entity.get();
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        long longId = (long) id;
        taskRepository.deleteById(longId);
        //tasksDataList.remove(id - 1);
        return "";
    }

    @PostMapping("/add")
    public TaskDescription add(
            @RequestParam(value = "name", required = true) String name){
        TaskDescription taskDescription = new TaskDescription(name);
        if (nameController.checkName(name))
            return null;
       // Optional<TaskDescription> entity =
        taskRepository.save(taskDescription);

        return taskDescription;
    }
}
