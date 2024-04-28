package ru.backend.security.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/api/v1/index-controller")
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "auth/index";
    }
}