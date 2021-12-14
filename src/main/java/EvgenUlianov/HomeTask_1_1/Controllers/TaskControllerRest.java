package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.NameController;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionWeb;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksDataList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("taskRest")
public class TaskControllerRest {
    //http://localhost:54322/

    final private TasksDataList tasksDataList;
    final private NameController nameController;

    @GetMapping
    public List<TaskDescriptionWeb> tasks(){
        return tasksDataList.getTasksWeb((taskDescription) -> true);
    }

    @GetMapping("/{id}")
    public TaskDescriptionWeb getTask(@PathVariable Integer id){
        return  tasksDataList.getWeb(id - 1);
    }

    @PatchMapping("/toggle/{id}")
    public TaskDescriptionWeb toggle(@PathVariable Integer id){
        TaskDescriptionWeb taskDescriptionWeb = tasksDataList.getWeb(id - 1);
        if (taskDescriptionWeb != null)
            taskDescriptionWeb.toggle();
        return taskDescriptionWeb;
    }

    @PatchMapping("/edit/{id}")
    public TaskDescriptionWeb edit(@PathVariable Integer id,
                       @RequestParam(value = "name", required = true) String name){
        if (nameController.checkName(name))
            return null;
        TaskDescriptionWeb taskDescriptionWeb = tasksDataList.getWeb(id - 1);
        if (taskDescriptionWeb != null)
            taskDescriptionWeb.setName(name);

        return taskDescriptionWeb;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        tasksDataList.remove(id - 1);
        return "";
    }

    @PostMapping("/add")
    public TaskDescriptionWeb add(
            @RequestParam(value = "name", required = true) String name){
        if (nameController.checkName(name))
            return null;
        return tasksDataList.add(name);
    }
}
