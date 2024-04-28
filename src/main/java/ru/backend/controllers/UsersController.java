package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.services.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

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
