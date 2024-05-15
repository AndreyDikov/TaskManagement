package ru.backend.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.backend.security.user.SecurityUser;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute("currentUser")
    public SecurityUser getCurrentUser(@AuthenticationPrincipal UserDetails currentUser) {
        return (SecurityUser)currentUser;
    }
}
