package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.UserManager.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("userRest")
public class UserControllerRest {

    //http://localhost:54322/

    final private UserService service;

    @PostMapping("/add")
    public User registration(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "password", required = true) String password){
        User user = service.add(name, password);
        if (user == null){
            return null;
        }
        return user;
    }

}
