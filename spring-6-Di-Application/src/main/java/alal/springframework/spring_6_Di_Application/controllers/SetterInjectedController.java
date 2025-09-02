package alal.springframework.spring_6_Di_Application.controllers;

import alal.springframework.spring_6_Di_Application.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {
    @Autowired
    private GreetingService greetingService;

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String SayHello() {
        return greetingService.sayGreeting();
    }
}
