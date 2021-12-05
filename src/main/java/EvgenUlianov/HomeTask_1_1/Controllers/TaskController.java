package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.Commands.Command;
import EvgenUlianov.HomeTask_1_1.IO.IOStream;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TaskController {

    //http://localhost:54322/

    final private TasksDataList tasksDataList;
    final private IOStream ioStream;
    final private List<Command> commandsList;
    private Map<String, Command> commands = new TreeMap<>();

    private void initialisingMap(){
        if (commands.isEmpty())
            commandsList.stream().forEach((command)->commands.put(command.getName(),    command));
    }

//    private String returnTasks(Model model){
//    }

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
        //return returnTasks(model);
    }
    @PostMapping("/task/toggle/{id}")
    public String toggle(@PathVariable Integer id, Model model){
        initialisingMap();
        commands.get("toggle").accept(Integer.toString(id));
        return "redirect:/";
        //return returnTasks(model);
    }

    @PostMapping("/task/edit/{id}")
    public String edit(@PathVariable Integer id, String name, Model model){
        initialisingMap();
        commands.get("edit").accept(String.format("%s %s", Integer.toString(id), name));
        TaskDescription taskDescription = tasksDataList.get(Integer.toString(id));
        TaskDescriptionWeb task = new TaskDescriptionWeb(id, taskDescription);
        model.addAttribute("task", task);
        return "task";
        //return returnTasks(model);
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        initialisingMap();
        commands.get("delete").accept(Integer.toString(id));
        return "redirect:/";
        //return returnTasks(model);
    }

    @PostMapping("/tasks/add")
    public String add(String name){
        initialisingMap();
        commands.get("add").accept(name);
        return "redirect:/";
        //return returnTasks(model);
    }

//    @PatchMapping("/task/toggle/{id}")
}
