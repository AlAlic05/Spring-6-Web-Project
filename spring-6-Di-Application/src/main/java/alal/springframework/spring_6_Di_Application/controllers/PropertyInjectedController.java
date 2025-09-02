package alal.springframework.spring_6_Di_Application.controllers;

import alal.springframework.spring_6_Di_Application.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@Controller
public class PropertyInjectedController {
    @Autowired
    GreetingService greetingService;

    public String SayHello(){
        return greetingService.sayGreeting();
    }
}
