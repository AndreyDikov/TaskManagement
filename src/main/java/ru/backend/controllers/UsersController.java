package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @RequestMapping("/add-user")
    public String getAddUserPage() {
        return "profile_editor";
    }
}
