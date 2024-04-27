package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationController() {
    }

    @RequestMapping
    public String getAuthenticationPage() {
        return "authentication";
    }
}
