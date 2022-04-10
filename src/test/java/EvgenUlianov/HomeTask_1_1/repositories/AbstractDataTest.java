package EvgenUlianov.HomeTask_1_1.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@Testcontainers
public class AbstractDataTest {

    @Container
    public static MyPostgresqlContainer postgreSQLContainer = MyPostgresqlContainer.getInstance();

    @Test
    public void testContainersIsRunning(){

        assertTrue(postgreSQLContainer.isRunning());
    }

}
