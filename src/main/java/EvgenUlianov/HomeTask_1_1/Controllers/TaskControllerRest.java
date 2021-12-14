package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TasksOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("taskRest")
public class TaskControllerRest {
    //http://localhost:54322/

    final private TasksOperator tasksOperator;

    @GetMapping
    public Iterable<TaskDescription> tasks(){
        return tasksOperator.tasks();
    }

    @GetMapping("/{id}")
    public TaskDescription getTask(@PathVariable Integer id){
        return tasksOperator.getTask(id);
    }

    @PatchMapping("/toggle/{id}")
    public TaskDescription toggle(@PathVariable Integer id){
        return tasksOperator.toggle(id);
    }

    @PatchMapping("/edit/{id}")
    public TaskDescription edit(@PathVariable Integer id,
                       @RequestParam(value = "name", required = true) String name){
        return tasksOperator.edit(id, name);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        tasksOperator.delete(id);
        return "";
    }

    @PostMapping("/add")
    public TaskDescription add(
            @RequestParam(value = "name", required = true) String name){
        return tasksOperator.add(name);
    }
}
