package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.Task;
import ru.backend.entities.User;
import ru.backend.enums.TaskStatus;
import ru.backend.security.user.Role;
import ru.backend.security.user.SecurityUser;
import ru.backend.services.TaskService;
import ru.backend.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/upcoming-tasks")
@AllArgsConstructor
public class UpcomingTasksController {

    private final TaskService taskService;
    private final UserService userService;

    @RequestMapping
    public String getUpcomingTasks(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        List<Task> upcomingTasks = taskService.getUpcomingTasks(userDetails);
        model.addAttribute("upcomingTasks", upcomingTasks);
        model.addAttribute("isAdmin", userService
                .getUser(userDetails)
                .getSecurityUser()
                .getRoles()
                .contains(Role.ADMIN));
        return "upcoming_tasks";
    }

    @GetMapping("/create-task")
    public String createTask(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.getAllUsers());
        return "add_or_update_task";
    }

    @PostMapping("/add-task")
    public String addTask(@AuthenticationPrincipal UserDetails userDetails,
                          Task task) {
        User user = ((SecurityUser) userDetails).getUser();
        taskService.saveTask(user, task);
        return "redirect:/upcoming-tasks";
    }

    @GetMapping("update-to-complete/{id}")
    public String updateToComplete(@PathVariable Long id) {
        taskService.updateStatus(id, TaskStatus.COMPLETED);
        return "redirect:/upcoming-tasks";
    }

    @GetMapping("update-to-failed/{id}")
    public String updateToFailed(@PathVariable Long id) {
        taskService.updateStatus(id, TaskStatus.FAILED);
        return "redirect:/upcoming-tasks";
    }
}
