package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.Task;
import ru.backend.services.TaskService;

import java.util.List;

@Controller
@RequestMapping("/upcoming-tasks")
@AllArgsConstructor
public class UpcomingTasksController {

    private final TaskService taskService;

    @RequestMapping
    public String getUpcomingTasks(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        List<Task> upcomingTasks = taskService.getUpcomingTasks(userDetails);
        model.addAttribute("upcomingTasks", upcomingTasks);
        return "upcoming_tasks";
    }

    @GetMapping("/create-task")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        return "add_or_update_task";
    }

    @PostMapping("/add-task")
    public String addTask(Task task) {
        taskService.saveTask(task);
        return "redirect:/upcoming-tasks";
    }
}
