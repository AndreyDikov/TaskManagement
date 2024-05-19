package ru.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.backend.entities.Task;
import ru.backend.enums.TaskStatus;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("""
            SELECT t
            FROM Task t
            WHERE (t.fromUser.id = :id
                OR t.toUser.id = :id)
                AND t.status = :status
            """)
    List<Task> findByUserIdAndStatus(@Param("id") Long id, @Param("status") TaskStatus status);
}
