package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    public UsersController() {
    }

    @RequestMapping
    public String getUsersPage() {
        return "users";
    }

    @RequestMapping("/add-user")
    public String getAddUserPage() {
        return "profile_editor";
    }
}
