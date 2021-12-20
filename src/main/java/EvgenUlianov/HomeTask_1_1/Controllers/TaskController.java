package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionService;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    final private TaskDescriptionService service;

    @GetMapping("/")
    public String tasks(Model model,
                        @AuthenticationPrincipal User user){
        Iterable<TaskDescription> repositoryAll = service.tasks(user);
        List<TaskDescriptionWeb> tasks = new ArrayList<>();
        repositoryAll.forEach(taskDescription -> tasks.add(new TaskDescriptionWeb(taskDescription)));
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable Integer id,
                          Model model,
                          @AuthenticationPrincipal User user){
       model.addAttribute("task", new TaskDescriptionWeb(service.getTask(id, user)));
        return "task";
    }

    @PostMapping("/task/toggle/{id}")
    public String toggle(@PathVariable Integer id,
                         Model model,
                         @AuthenticationPrincipal User user){
        service.toggle(id, user);
        return "redirect:/";
    }

    @PostMapping("/task/edit/{id}")
    public String edit(@PathVariable Integer id,
                       String name,
                       Model model,
                       @AuthenticationPrincipal User user){
        model.addAttribute("task", new TaskDescriptionWeb(service.edit(id, name, user)));
        return "task";
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable Integer id,
                         Model model,
                         @AuthenticationPrincipal User user){
        service.delete(id, user);
        return "redirect:/";
    }

    @PostMapping("/tasks/add")
    public String add(String name,
                      @AuthenticationPrincipal User user){
        service.add(name, user);

        return "redirect:/";
    }

}
