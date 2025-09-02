package alal.springframework.spring_6_Di_Application.controllers;

import alal.springframework.spring_6_Di_Application.services.GreetingService;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

public class PropertyInjectedController {
    GreetingService greetingService;

    public String SayHello(){
        return greetingService.sayGreeting();
    }
}
