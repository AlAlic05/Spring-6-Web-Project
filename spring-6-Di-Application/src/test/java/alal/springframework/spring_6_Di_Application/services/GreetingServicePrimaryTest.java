package alal.springframework.spring_6_Di_Application.services;

import alal.springframework.spring_6_Di_Application.controllers.ConstructorInjectedController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingServicePrimaryTest {

    @Autowired
    ConstructorInjectedController controller;

    @Test
    void sayGreeting() {
        String greeting = controller.sayHello();
        System.out.println(greeting);
        assertEquals("Hello from the PRIMARY Bean ;D", greeting);
    }
}