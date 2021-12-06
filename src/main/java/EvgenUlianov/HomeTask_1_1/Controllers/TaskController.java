package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    //http://localhost:54322/

    final private TasksDataList tasksDataList;
    final private NameController nameController;

    @GetMapping("/")
    public String tasks(Model model){
        model.addAttribute("tasks", tasksDataList.getTasksWeb((taskDescription) -> true));
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String getTask(@PathVariable Integer id, Model model){
        TaskDescription taskDescription = tasksDataList.get(Integer.toString(id));
        TaskDescriptionWeb task = new TaskDescriptionWeb(id, taskDescription);
        model.addAttribute("task", task);
        return "task";
    }
    @PostMapping("/task/toggle/{id}")
    public String toggle(@PathVariable Integer id, Model model){
        TaskDescription taskDescription = tasksDataList.get(id - 1);
        if (taskDescription != null)
            taskDescription.toggle();
        return "redirect:/";
    }

    @PostMapping("/task/edit/{id}")
    public String edit(@PathVariable Integer id, String name, Model model){
        if (nameController.checkName(name))
            return "redirect:/";
        TaskDescription taskDescription = tasksDataList.get(id - 1);
        if (taskDescription != null)
            taskDescription.setName(name);

        TaskDescriptionWeb task = new TaskDescriptionWeb(id, taskDescription);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        tasksDataList.remove(id - 1);
        return "redirect:/";
    }

    @PostMapping("/tasks/add")
    public String add(String name){
        if (nameController.checkName(name)) return "redirect:/";
        TaskDescription taskDescription = new TaskDescription(name);
        tasksDataList.add(taskDescription);

        return "redirect:/";
    }

}
