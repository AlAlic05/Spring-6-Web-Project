package alal.springframework.spring_6_Di_Application.services;

import alal.springframework.spring_6_Di_Application.controllers.PropertyInjectedController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("propertyGreetingService")
public class GreetingServicePropertyInjected implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello World as a Property ToT";
    }
}
