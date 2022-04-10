package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.Security.CustomPasswordEncoder;
import EvgenUlianov.HomeTask_1_1.Security.CustomUserDetailsService;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescriptionService;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.UserManager.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskControllerRest.class)
class TaskControllerRestIntegratedTest {

    TaskControllerRestIntegratedTest() throws JsonProcessingException {
    }


    private static final long DEFAULT_ID = 1L;
    private static final int DEFAULT_ID_INTEGER = 1;


    private User getTestUser(){
        User user = new User();
        user.setRole("USER");
        user.setUsername("User");
        user.setPassword("1");
        user.setId(DEFAULT_ID);
        return user;
    }

    private TaskDescription getTestTaskDescription(){
        TaskDescription taskDescription = new TaskDescription("Test");
        taskDescription.setOwner(testUser);
        return taskDescription;
    }

    private String getTestTaskDescriptionJson() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer();
        return ow.writeValueAsString(expectedTaskDescription);
    }

    private final User testUser = getTestUser();
    private final TaskDescription expectedTaskDescription = getTestTaskDescription();
    private final String expectedTaskDescriptionJson = getTestTaskDescriptionJson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDescriptionService service;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private CustomPasswordEncoder passwordEncoder;

    @Test
    void tasksTest() throws Exception {
        List<TaskDescription> expectedTasks = new ArrayList<>();
        expectedTasks.add(expectedTaskDescription);
        when(service.tasks(testUser)).thenReturn(expectedTasks);

        ResultActions resultActions = this.mockMvc.perform(get("/taskRest").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(containsString(expectedTaskDescriptionJson)));
    }

    @Test
    void getTaskTest() throws Exception {
        when(service.getTask(DEFAULT_ID_INTEGER, testUser)).thenReturn(expectedTaskDescription);

        ResultActions resultActions = this.mockMvc.perform(get("/taskRest/1").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(containsString(expectedTaskDescriptionJson)));
    }

    @Test
    void toggleTest() throws Exception {
        when(service.toggle(DEFAULT_ID_INTEGER, testUser)).thenReturn(expectedTaskDescription);

        ResultActions resultActions = this.mockMvc.perform(patch("/taskRest/toggle/1").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(containsString(expectedTaskDescriptionJson)));
    }

    @Test
    void editTest() throws Exception {
        when(service.edit(DEFAULT_ID_INTEGER, "Test1", testUser)).thenReturn(expectedTaskDescription);

        ResultActions resultActions = this.mockMvc.perform(patch("/taskRest/edit/1?name=Test1").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(containsString(expectedTaskDescriptionJson)));
    }

    @Test
    void deleteTest() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(delete("/taskRest/delete/1").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
    }

    @Test
    void addTest() throws Exception {
        when(service.add("Test1", testUser)).thenReturn(expectedTaskDescription);

        ResultActions resultActions = this.mockMvc.perform(post("/taskRest/add?name=Test1").with(user(testUser) ));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().string(containsString(expectedTaskDescriptionJson)));
    }
}