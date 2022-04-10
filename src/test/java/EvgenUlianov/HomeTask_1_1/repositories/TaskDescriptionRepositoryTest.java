package EvgenUlianov.HomeTask_1_1.repositories;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import EvgenUlianov.HomeTask_1_1.UserManager.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;

//@DataJpaTest
//@Testcontainers
public class TaskDescriptionRepositoryTest {

//    @Container
//    public static MyPostgresqlContainer postgreSQLContainer = MyPostgresqlContainer.getInstance();
//
//    private static final long DEFAULT_ID = 1L;
//    private static final int DEFAULT_ID_INTEGER = 1;
//
//    private User getTestUser(){
//        User user = new User();
//        user.setRole("USER");
//        user.setUsername("User");
//        user.setPassword("1");
//        user.setId(DEFAULT_ID);
//        return user;
//    }
//
//    private TaskDescription getTestTaskDescription(){
//        TaskDescription taskDescription = new TaskDescription("Test");
//        taskDescription.setOwner(testUser);
//        return taskDescription;
//    }
//
//    private final User testUser = getTestUser();
//    private final TaskDescription expectedTaskDescription = getTestTaskDescription();
//
////    @Test
////    @Transactional
//    public void testContainersIsRunning(){
//
////        taskRepository.save(expectedTaskDescription);
//
//        assertTrue(postgreSQLContainer.isRunning());
//    }
}
