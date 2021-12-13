package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    //http://localhost:54322/

    final private TaskRepository taskRepository;
    final private NameController nameController;

    @GetMapping("/")
    public String tasks(Model model){

        Iterable<TaskDescription> repositoryAll = taskRepository.findAll();


        //уверен, что есть способ лучше, но я пока не изучил, как получить в Java даанные изначально в отсортированном виде
        Map<Long, TaskDescriptionWeb> tasks = new TreeMap<>();
        repositoryAll.forEach(taskDescription -> tasks.put(taskDescription.getId(), new TaskDescriptionWeb(taskDescription)));
        //tasks.values()
        model.addAttribute("tasks", tasks.values());
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable Integer id, Model model){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        model.addAttribute("task", new TaskDescriptionWeb(entity.get()));
        return "task";
    }
    @PostMapping("/task/toggle/{id}")
    public String toggle(@PathVariable Integer id, Model model){
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);
        if (entity != null){
            entity.get().toggle();
            taskRepository.save(entity.get());
        }
        return "redirect:/";
    }

    @PostMapping("/task/edit/{id}")
    public String edit(@PathVariable Integer id, String name, Model model){
        if (nameController.checkName(name))
            return "redirect:/";
        long longId = (long) id;
        Optional<TaskDescription> entity = taskRepository.findById(longId);

        if (entity != null){
            entity.get().setName(name);
            taskRepository.save(entity.get());
        }

        model.addAttribute("task", new TaskDescriptionWeb(entity.get()));
        return "task";
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        long longId = (long) id;
        taskRepository.deleteById(longId);
        return "redirect:/";
    }

    @PostMapping("/tasks/add")
    public String add(String name){
        if (nameController.checkName(name)) return "redirect:/";
        TaskDescription taskDescription = new TaskDescription(name);
        if (nameController.checkName(name))
            return null;
        // Optional<TaskDescription> entity =
        taskRepository.save(taskDescription);

        return "redirect:/";
    }

}
