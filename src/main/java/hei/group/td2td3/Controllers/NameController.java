package hei.group.td2td3.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
   @GetMapping("/welcome")
    public String welcomeName(@RequestParam String name) {
        return "welcome "+name;
    }
}
