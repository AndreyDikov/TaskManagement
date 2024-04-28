package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController2 {

    @RequestMapping
    public String getAuthenticationPage() {
        return "authentication";
    }
}
