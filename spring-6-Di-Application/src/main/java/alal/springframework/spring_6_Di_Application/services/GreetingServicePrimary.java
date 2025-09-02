package alal.springframework.spring_6_Di_Application.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class GreetingServicePrimary implements GreetingService {
    @Override
    public String sayGreeting() {
        return "Hello from the PRIMARY Bean ;D";
    }
}
