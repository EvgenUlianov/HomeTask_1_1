package EvgenUlianov.HomeTask_1_1.TaskManager;

import EvgenUlianov.HomeTask_1_1.Controllers.TaskControllerRest;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.repositories.TaskDescriptionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;

import java.util.*;

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

        assertSimpleTest("findOwnById");
    }

    @Test
    void tasksVoid() {

        // initialization
        User nullUser = null;

        // run test
        Iterable<TaskDescription> actualTaskDescriptions = service.tasks(nullUser);
        assertEquals(0, ((List)actualTaskDescriptions).size());
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
        assertSimpleTest("findOwn");

    }

    @Test
    void toggleValidTask() {

        // initialization
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);

        // run test
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);
        assertTrue(actualTaskDescription.isCompleted());
        assertHardTest(new ArrayList<String>(Arrays.asList(new String[]{"findOwnById", "save"})));
    }

    @Test
    void toggleNoTask() {

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(null);

        // run test
        TaskDescription actualTaskDescription = service.toggle(DEFAULT_ID_INTEGER, testUser);
        assertNull(actualTaskDescription);
        assertSimpleTest("findOwnById");
    }

    @Test
    void editValidString() {

        // initialization
        Optional<TaskDescription> expectedEntity = Optional.of(expectedTaskDescription);
        String expectedName = "test new";

        // mockito
        Mockito.when(repository.findOwnById(DEFAULT_ID, DEFAULT_ID))
                .thenReturn(expectedEntity);

        // run test
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);
        Assertions.assertEquals(expectedName, actualTaskDescription.getName());

        assertHardTest(new ArrayList<String>(Arrays.asList(new String[]{"findOwnById", "save"})));
    }

    @Test
    void editEmptyString() {

        // initialization
        String expectedName = "";

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

        // run test
        TaskDescription actualTaskDescription = service.edit(DEFAULT_ID_INTEGER, expectedName, testUser);
        assertNull(actualTaskDescription);
        assertSimpleTest("findOwnById");
    }

    @Test
    void delete() {

        // run test
        assertDoesNotThrow(()->{service.delete(DEFAULT_ID_INTEGER, testUser);});
        assertSimpleTest("deleteOwnById");
    }

    @Test
    void addValidString() {

        // initialization
        String expectedName = "test new";

        ArgumentCaptor<TaskDescription> argumentCaptor = ArgumentCaptor.forClass(TaskDescription.class);

        // run test
        service.add(expectedName, testUser);

        Mockito.verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(expectedName, argumentCaptor.getValue().getName());
        assertSimpleTest("save");
    }

    @Test
    void addEmptyString() {

        // initialization
        String expectedName = "";

        // run test
        service.add(expectedName, testUser);

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(0, invocations.size());
    }


    private void assertSimpleTest(String methodName){

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods
        assertEquals(1, invocations.size());
        // check the name of called method
        if (invocations.size() == 1)
            Assertions.assertEquals(methodName, ((Invocation) invocations.toArray()[0]).getMethod().getName());
    }

    private void assertHardTest(List<String> methodNamesExtended){

        Collection<Invocation> invocations = Mockito.mockingDetails(repository).getInvocations();
        // just a number of calls of any mock's methods

        List<String> methodNamesActual  = new ArrayList<>();
        for (Invocation invocation: invocations)
            methodNamesActual.add(invocation.getMethod().getName());

        assertIterableEquals(methodNamesExtended, methodNamesActual );
        // check the name of called method
//        if (invocations.size() == 1)
//            Assertions.assertEquals(methodName, ((Invocation) invocations.toArray()[0]).getMethod().getName());
    }


}