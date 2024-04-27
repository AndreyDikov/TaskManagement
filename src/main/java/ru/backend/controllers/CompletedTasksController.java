package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/completed-tasks")
public class CompletedTasksController {

    @Autowired
    public CompletedTasksController() {
    }

    @RequestMapping
    public String getCompletedTasksPage() {
        return "completed_tasks";
    }

    @RequestMapping("/task-info")
    public String getTaskInfoPage() {
        return "completed_or_failed_task";
    }
}
