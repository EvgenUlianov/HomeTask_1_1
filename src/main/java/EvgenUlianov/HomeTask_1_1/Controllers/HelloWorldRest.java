package EvgenUlianov.HomeTask_1_1.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("hello")
public class HelloWorldRest {

    @GetMapping("/")
    public String  helloWorld(){
        return "Hello, World";
    }

}
