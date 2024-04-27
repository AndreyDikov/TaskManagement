package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upcoming-tasks")
public class UpcomingTasksController {

    @Autowired
    public UpcomingTasksController() {
    }

    @RequestMapping
    public String getUpcomingTasks() {
        return "upcoming_tasks";
    }

    @RequestMapping("/add-or-update-task")
    public String addTask() {
        return "add_or_update_task";
    }
}
