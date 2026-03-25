package hei.group.td2td3.Service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    public String welcome(String name) {
        return "Welcome " + name;
    }
}
