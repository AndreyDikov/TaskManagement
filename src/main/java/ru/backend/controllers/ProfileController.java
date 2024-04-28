package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.User;
import ru.backend.services.UserService;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping("/edit-profile")
    public String getProfileEditorPage(@AuthenticationPrincipal UserDetails userDetails,
                                       Model model) {

        User user = userService.getUser(userDetails);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin",
                user.getSecurityUser().getRoles().contains("ADMIN"));

        return "profile_editor";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails, User user) {
        User userProfile = userService.getUser(userDetails);
        userService.updateUser(user, userProfile.getId());
        return "redirect:/";
    }
}
