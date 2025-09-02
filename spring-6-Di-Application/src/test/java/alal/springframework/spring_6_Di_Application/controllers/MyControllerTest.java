package alal.springframework.spring_6_Di_Application.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyControllerTest {

    @Test
    void sayHello() {
        MyController myController = new MyController();
        String greeting = myController.sayHello();
        System.out.println(greeting);
        assertEquals("Hello World", greeting);
    }
}