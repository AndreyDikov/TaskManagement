package ru.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.backend.entities.Task;
import ru.backend.security.user.Role;
import ru.backend.services.TaskService;
import ru.backend.services.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/completed-tasks")
public class CompletedTasksController {

    private final TaskService taskService;
    private final UserService userService;

    @RequestMapping
    public String getCompletedTasksPage(@AuthenticationPrincipal UserDetails userDetails,
                                        Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks(userDetails);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("isAdmin", userService
                .getUser(userDetails)
                .getSecurityUser()
                .getRoles()
                .contains(Role.ADMIN));
        return "completed_tasks";
    }

    @RequestMapping("/task-info")
    public String getTaskInfoPage() {
        return "completed_or_failed_task";
    }
}
