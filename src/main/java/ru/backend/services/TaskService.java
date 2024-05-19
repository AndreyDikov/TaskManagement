package ru.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.backend.entities.Task;
import ru.backend.entities.User;
import ru.backend.enums.TaskStatus;
import ru.backend.repositories.TaskRepository;
import ru.backend.security.user.SecurityUser;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getUpcomingTasks(UserDetails userDetails) {
        return taskRepository.findByUserIdAndStatus(((SecurityUser) userDetails).getId(), TaskStatus.UPCOMING);
    }

    public List<Task> getCompletedTasks(UserDetails userDetails) {
        return taskRepository.findByUserIdAndStatus(((SecurityUser) userDetails).getId(), TaskStatus.COMPLETED);
    }

    public List<Task> getFailedTasks(UserDetails userDetails) {
        return taskRepository.findByUserIdAndStatus(((SecurityUser) userDetails).getId(), TaskStatus.FAILED);
    }

    public void saveTask(User fromUser, Task task) {
        if (task.getToUser() == null) {
            task.setToUser(fromUser);
        }
        task.setFromUser(fromUser);
        task.setStatus(TaskStatus.UPCOMING);
        taskRepository.save(task);
    }

    public void updateStatus(Long id, TaskStatus status) {
        Optional<Task> taskOption = taskRepository.findById(id);
        if (taskOption.isPresent()) {
            Task task = taskOption.get();
            task.setStatus(status);
            taskRepository.save(task);
        }
    }
}
