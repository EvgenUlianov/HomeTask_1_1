package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionService;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskControllerRestTest {

    private static final long DEFAULT_ID = 1L;

    private User getTestUser(){
        User user = new User();
        user.setRole("USER");
        user.setUsername("User");
        user.setPassword("1");
        user.setId(DEFAULT_ID);
        return user;
    }

    private void runSimpleTest(String methodName){
        // initialization
        User testUser = getTestUser();

        // mockito
        TaskDescriptionService service = Mockito.mock(TaskDescriptionService.class);

        // run test
        TaskControllerRest controller = new TaskControllerRest(service);
        switch (methodName){
            case "tasks": {controller.tasks(testUser);} break;
            case "getTask": {controller.getTask(1, testUser);}break;
            case "toggle": {controller.toggle(1, testUser);}break;
            case "edit": {controller.edit(1, "name", testUser);}break;
            case "delete": {controller.delete(1, testUser);}break;
            case "add": {controller.add("name", testUser);}break;
        }

        Collection<Invocation> invocations = Mockito.mockingDetails(service).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(1, invocations.size());

    }

    @Test
    void tasks() {
        runSimpleTest("tasks");
    }

    @Test
    void getTask() {
        runSimpleTest("getTask");
    }

    @Test
    void toggle() {
        runSimpleTest("toggle");
    }

    @Test
    void edit() {
        runSimpleTest("edit");
    }

    @Test
    void delete() {
        runSimpleTest("delete");
    }

    @Test
    void add() {
        runSimpleTest("add");
    }
}