package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProfileController {

    @Autowired
    public ProfileController() {
    }

    @RequestMapping
    public String getProfilePage() {
        return "profile";
    }

    @RequestMapping("/edit")
    public String getProfileEditorPage() {
        return "profile_editor";
    }
}
