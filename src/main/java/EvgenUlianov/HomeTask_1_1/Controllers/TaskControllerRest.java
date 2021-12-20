package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionService;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("taskRest")
public class TaskControllerRest {
    //http://localhost:54322/

    final private TaskDescriptionService service;

    @GetMapping
    public Iterable<TaskDescription> tasks(
            @AuthenticationPrincipal User user){
        return service.tasks(user);
    }

    @GetMapping("/{id}")
    public TaskDescription getTask(@PathVariable Integer id,
                                   @AuthenticationPrincipal User user){
        return service.getTask(id, user);
    }

    @PatchMapping("/toggle/{id}")
    public TaskDescription toggle(@PathVariable Integer id,
                                  @AuthenticationPrincipal User user){
        return service.toggle(id, user);
    }

    @PatchMapping("/edit/{id}")
    public TaskDescription edit(@PathVariable Integer id,
                                @RequestParam(value = "name", required = true) String name,
                                @AuthenticationPrincipal User user){
        return service.edit(id, name, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id,
                         @AuthenticationPrincipal User user){
        service.delete(id, user);
        return "";
    }

    @PostMapping("/add")
    public TaskDescription add(@RequestParam(value = "name", required = true) String name,
                               @AuthenticationPrincipal User user){
        return service.add(name, user);
    }
}
