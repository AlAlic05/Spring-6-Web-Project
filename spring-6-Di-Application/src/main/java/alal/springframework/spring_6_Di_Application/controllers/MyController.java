package alal.springframework.spring_6_Di_Application.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    public String sayHello(){
        System.out.println("Im in the Controller :D");
        return "Hello World";
    }
}
