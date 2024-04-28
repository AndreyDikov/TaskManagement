package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.backend.entities.User;
import ru.backend.security.user.Role;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            from User u
            join SecurityUser su
            on u.securityUser = su
            where :role member of su.roles
            """)
    List<User> findByRole(@Param("role") Role role);
}
