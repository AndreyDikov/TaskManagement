package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.User;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ProfileController {

    @GetMapping
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping("/add-or-update-user-page")
    public String getProfileEditorPage(Model model) {
        model.addAttribute("userDTO", new User());
        return "profile_editor";
    }
}
