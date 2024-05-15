package ru.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.backend.entities.Task;
import ru.backend.repositories.TaskRepository;
import ru.backend.security.user.SecurityUser;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getUpcomingTasks(UserDetails userDetails) {
        return taskRepository.findByUserId(((SecurityUser) userDetails).getId());
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }
}
