package alal.springframework.spring_6_Di_Application;

import alal.springframework.spring_6_Di_Application.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6DiApplicationTests {
    @Autowired
    ApplicationContext ctx;

    @Autowired
    MyController myController;

    @Test
    void testAutowireOfController() {
        System.out.println(myController.sayHello());
        assert myController.sayHello().equals("Hello World");
    }

    @Test
    void testGetControllerFromContext() {
        MyController myController = ctx.getBean(MyController.class);
        System.out.println(myController.sayHello());
        assert myController.sayHello().equals("Hello World");
    }

	@Test
	void contextLoads() {
	}

}
