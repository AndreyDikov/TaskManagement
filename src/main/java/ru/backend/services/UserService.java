package ru.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.backend.entities.User;
import ru.backend.enums.Role;
import ru.backend.enums.UserStatus;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM users WHERE role = 'USER'", (ResultSet rs, int rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setJobTitle(rs.getString("job_title"));
            user.setContacts(rs.getString("contacts"));
            user.setStatus(rs.getString("status").equals("ACTIVE")
                    ? UserStatus.ACTIVE
                    : UserStatus.DELETED);
            user.setRole(Role.USER);
            users.add(user);
            return user;
        });
        return users;
    }
}
