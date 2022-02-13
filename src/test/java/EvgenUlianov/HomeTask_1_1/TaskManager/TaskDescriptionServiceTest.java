package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.repositories.TaskDescriptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.stubbing.Answer;

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



    @Test
    void tasks() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.getTask(DEFAULT_ID_INTEGER, testUser);

        assertEquals(expectedTaskDescription, actualTaskDescription);

    }

    @Test
    void tasksVoid() {

        // initialization
        User nullUser = null;
        TaskDescription testTaskDescription = getTestTaskDescription();
        List<TaskDescription> expectedTaskDescriptions = new ArrayList(1);

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwn(DEFAULT_ID))
                .thenReturn(expectedTaskDescriptions);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        Iterable<TaskDescription> actualTaskDescriptions = service.tasks(nullUser);

        assertIterableEquals(expectedTaskDescriptions, actualTaskDescriptions);

    }

    @Test
    void getTask() {

        // initialization
        User testUser = getTestUser();
        TaskDescription testTaskDescription = getTestTaskDescription();
        List<TaskDescription> expectedTaskDescriptions = new ArrayList(1);
        expectedTaskDescriptions.add(testTaskDescription);

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwn(DEFAULT_ID))
                .thenReturn(expectedTaskDescriptions);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        Iterable<TaskDescription> actualTaskDescriptions = service.tasks(testUser);

        assertIterableEquals(expectedTaskDescriptions, actualTaskDescriptions);

    }

    @Test
    void toggle() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);

        assertTrue(actualTaskDescription.isCompleted());
    }

    @Test
    void toggleNoTask() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(null);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);

        assertNull(actualTaskDescription);
    }

    @Test
    void edit() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "test new";

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);

//        assertTrue(actualTaskDescription.getName().equals(expectedName));
        Assertions.assertEquals(expectedName, actualTaskDescription.getName());
    }

    @Test
    void editEmptyString() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "";

        // mockito
        TaskDescriptionRepository repository = Mockito.spy(TaskDescriptionRepository.class);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(0, invocations.size());
        assertNull(actualTaskDescription);
    }

    @Test
    void editNoTask() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "test new";

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(null);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);

        assertNull(actualTaskDescription);
    }

    @Test
    void delete() {

        // initialization
        User testUser = getTestUser();

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Answer<?> var1 = invocationOnMock -> {;
            return null;
        };
        Mockito.when(repository.deleteOwnById(DEFAULT_ID, DEFAULT_ID))
                .then(var1);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);

        assertDoesNotThrow(()->{service.delete(DEFAULT_ID_INTEGER, testUser);});
    }

    @Test
    void add() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "test new";

        // mockito
        TaskDescriptionRepository repository = Mockito.mock(TaskDescriptionRepository.class);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        ArgumentCaptor<TaskDescription> argumentCaptor = ArgumentCaptor.forClass(TaskDescription.class);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        service.add(expectedName, testUser);

        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(expectedName, argumentCaptor.getValue().getName());
    }

    @Test
    void addEmptyString() {

        // initialization
        User testUser = getTestUser();
        TaskDescription expectedTaskDescription = getTestTaskDescription();
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "";

        // mockito
        TaskDescriptionRepository repository = Mockito.spy(TaskDescriptionRepository.class);
        Mockito.when(repository.save(expectedTaskDescription))
                .thenReturn(expectedTaskDescription);

        // run test
        TaskDescriptionService service = new TaskDescriptionService(repository);
        service.add(expectedName, testUser);

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(0, invocations.size());
    }
}