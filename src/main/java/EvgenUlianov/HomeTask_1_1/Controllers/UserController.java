package EvgenUlianov.HomeTask_1_1.Controllers;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import EvgenUlianov.HomeTask_1_1.UserManager.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    //http://localhost:54322/

    final private UserService service;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        return "redirect:/";
    }


    @PostMapping("/registration")
    public String registration(User newUser, Model model){
        User user = service.add(newUser.getUsername(), newUser.getPassword());
        if (user == null){
            model.addAttribute("errorMessage", "не корректноый логин");
            return "login";
        }
        return "redirect:/";
    }


}
