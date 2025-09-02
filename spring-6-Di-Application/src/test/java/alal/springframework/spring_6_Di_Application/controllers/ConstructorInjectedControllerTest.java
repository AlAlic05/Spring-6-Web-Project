package alal.springframework.spring_6_Di_Application.controllers;

import alal.springframework.spring_6_Di_Application.services.GreetingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstructorInjectedControllerTest {
    @Autowired
    ConstructorInjectedController constructorInjectedController;

    @Test
    void sayHello() {
        String greeting = constructorInjectedController.sayHello();
        System.out.println(greeting);
        assertEquals("Hello World", greeting);
    }
}