package ru.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.backend.entities.User;
import ru.backend.enums.UserStatus;
import ru.backend.repositories.UserRepository;
import ru.backend.security.auth.UserDTO;
import ru.backend.security.user.Role;
import ru.backend.security.user.SecurityUser;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository securityUserRepository;

    public void saveOrUpdateUser(UserDTO userDTO) {

//        securityUserRepository.(userDTO);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM users WHERE role = 'USER'", (ResultSet rs, int rowNum) -> {
            User user = new User();
            SecurityUser securityUser = new SecurityUser();
            user.setSecurityUser(securityUser);
            securityUser.setSquadUser(user);
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setJobTitle(rs.getString("job_title"));
            user.setContacts(rs.getString("contacts"));
            securityUser.setPassword(rs.getString("password"));
            securityUser.setLogin(rs.getString("login"));
            securityUser.setActive(rs.getString("status").equals("ACTIVE"));
            securityUser.setRoles(Set.of(Role.USER));
            users.add(user);
            return user;
        });
        return users;
    }
}
