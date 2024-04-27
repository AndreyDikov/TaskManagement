package ru.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/failed-tasks")
public class FailedTasksController {

    @Autowired
    public FailedTasksController() {
    }

    @RequestMapping
    public String getFailedTasksPage() {
        return "failed_tasks";
    }

    @RequestMapping("/task-info")
    public String getTaskInfoPage() {
        return "completed_or_failed_task";
    }
}
