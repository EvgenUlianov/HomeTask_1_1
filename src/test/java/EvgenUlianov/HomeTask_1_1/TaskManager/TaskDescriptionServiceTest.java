package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.repositories.TaskDescriptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskDescriptionServiceTest {


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
        return new TaskDescription("Test");
    }

    private final User testUser = getTestUser();
    private final TaskDescription expectedTaskDescription = getTestTaskDescription();
    private final TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
    private TaskDescriptionService service;

    @BeforeEach
    void prepareTaskDescriptionService(){
        service = new TaskDescriptionService(repository);
    }

    @Test
    void tasksValid() {

        // initialization
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);

        // run test
        TaskDescription actualTaskDescription = service.getTask(DEFAULT_ID_INTEGER, testUser);
        assertEquals(expectedTaskDescription, actualTaskDescription);
    }

    @Test
    void tasksVoid() {

        // initialization
        User nullUser = null;
        List<TaskDescription> expectedTaskDescriptions = new ArrayList(1);

        // mockito
        Mockito.when(repository.findOwn(DEFAULT_ID))
                .thenReturn(expectedTaskDescriptions);

        // run test
        Iterable<TaskDescription> actualTaskDescriptions = service.tasks(nullUser);
        assertIterableEquals(expectedTaskDescriptions, actualTaskDescriptions);
    }

    @Test
    void getTask() {

        // initialization
        List<TaskDescription> expectedTaskDescriptions = new ArrayList(1);
        expectedTaskDescriptions.add(expectedTaskDescription);

        // mockito
        Mockito.when(repository.findOwn(DEFAULT_ID))
                .thenReturn(expectedTaskDescriptions);

        // run test
        Iterable<TaskDescription> actualTaskDescriptions = service.tasks(testUser);
        assertIterableEquals(expectedTaskDescriptions, actualTaskDescriptions);
    }

    @Test
    void toggleValidTask() {

        // initialization
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);

        assertTrue(actualTaskDescription.isCompleted());
    }

    @Test
    void toggleNoTask() {

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(null);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);

        assertNull(actualTaskDescription);
    }

    @Test
    void editValidString() {

        // initialization
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "test new";

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);
        Assertions.assertEquals(expectedName, actualTaskDescription.getName());
    }

    @Test
    void editEmptyString() {

        // initialization
        String expectedName = "";

        // mockito
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(0, invocations.size());
        assertNull(actualTaskDescription);
    }

    @Test
    void editNoTask() {

        // initialization
        String expectedName = "test new";

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(null);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);
        assertNull(actualTaskDescription);
    }

    @Test
    void delete() {

        // run test
        assertDoesNotThrow(()->{service.delete(DEFAULT_ID_INTEGER, testUser);});
    }

    @Test
    void addValidString() {

        // initialization
        String expectedName = "test new";

        // mockito
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        ArgumentCaptor<TaskDescription> argumentCaptor = ArgumentCaptor.forClass(TaskDescription.class);

        // run test
        service.add(expectedName, testUser);

        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(expectedName, argumentCaptor.getValue().getName());
    }

    @Test
    void addEmptyString() {

        // initialization
        String expectedName = "";

        // mockito
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        service.add(expectedName, testUser);

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(0, invocations.size());
    }
}