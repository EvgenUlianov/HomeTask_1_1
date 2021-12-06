package EvgenUlianov.HomeTask_1_1;


import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class HomeTask11Application implements CommandLineRunner {//

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HomeTask11Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Запуск программы Список задач");

    }
}
