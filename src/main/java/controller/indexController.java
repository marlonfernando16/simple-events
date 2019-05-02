package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public String index() {
        return "Just do it!!!???";
    }
}
