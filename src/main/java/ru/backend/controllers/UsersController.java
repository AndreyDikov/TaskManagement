package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.User;
import ru.backend.security.user.SecurityUser;
import ru.backend.services.UserService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @GetMapping
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/add-user")
    public String createUser(Model model) {
        User user = new User();
        SecurityUser securityUser = new SecurityUser();
        user.setSecurityUser(securityUser);
        securityUser.setUser(user);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", true);
        model.addAttribute("isAdd", true);
        return "profile_editor";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("isAdmin", true);
            model.addAttribute("isAdd", false);
        } else {
            throw new RuntimeException("User not found");
        }

        return "profile_editor";
    }

    @PostMapping("/create-or-update/{id}")
    public String createOrUpdateUser(@PathVariable Long id, User user) {
        if (id != 0) {
            userService.updateUser(user, id);
        } else {
            userService.save(user);
        }

        return "redirect:/users";
    }

    @GetMapping("/update-status/{id}")
    public String updateUserStatus(@PathVariable Long id) {
        userService.updateUserStatus(id);
        return "redirect:/users";
    }
}
