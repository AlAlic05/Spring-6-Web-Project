package alal.springframework.spring_6_Di_Application.services;

import org.springframework.stereotype.Service;

@Service("setterGreetingBean")
public class GreetingServiceSetterInjection implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello from the Setter Injection Bean ;D";
    }
}
