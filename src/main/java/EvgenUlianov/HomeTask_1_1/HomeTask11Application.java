package EvgenUlianov.HomeTask_1_1;


import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.UserManager.UserService;
import EvgenUlianov.HomeTask_1_1.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class HomeTask11Application implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HomeTask11Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Запуск программы Список задач");

        userService.addAdmin();

    }
}
