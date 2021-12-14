package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksOperator;
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

    final private TasksOperator tasksOperator;

    @GetMapping("/")
    public String tasks(Model model){
        Iterable<TaskDescription> repositoryAll = tasksOperator.tasks();
        List<TaskDescriptionWeb> tasks = new ArrayList<>();
        repositoryAll.forEach(taskDescription -> tasks.add(new TaskDescriptionWeb(taskDescription)));
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable Integer id, Model model){
       model.addAttribute("task", new TaskDescriptionWeb(tasksOperator.getTask(id)));
        return "task";
    }

    @PostMapping("/task/toggle/{id}")
    public String toggle(@PathVariable Integer id, Model model){
        tasksOperator.toggle(id);
        return "redirect:/";
    }

    @PostMapping("/task/edit/{id}")
    public String edit(@PathVariable Integer id, String name, Model model){
        model.addAttribute("task", new TaskDescriptionWeb(tasksOperator.edit(id, name)));
        return "task";
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        tasksOperator.delete(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/add")
    public String add(String name){
        tasksOperator.add(name);

        return "redirect:/";
    }

}
