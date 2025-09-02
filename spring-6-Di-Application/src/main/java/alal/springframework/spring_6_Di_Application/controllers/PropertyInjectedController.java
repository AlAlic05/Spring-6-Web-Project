package alal.springframework.spring_6_Di_Application.controllers;

import alal.springframework.spring_6_Di_Application.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

@Controller
public class PropertyInjectedController {
    @Qualifier("propertyGreetingService")
    @Autowired
    GreetingService greetingService;

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}
