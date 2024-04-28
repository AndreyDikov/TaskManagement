package ru.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.backend.entities.User;
import ru.backend.repositories.UserRepository;
import ru.backend.security.user.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findByRole(Role.USER);
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public void updateUser(User user, Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userEntity = userOptional.get();
            userEntity.getSecurityUser().setPassword(user.getSecurityUser().getPassword());
            userEntity.getSecurityUser().setLogin(user.getSecurityUser().getLogin());
            userEntity.setName(user.getName());
            userEntity.setSurname(user.getSurname());
            userEntity.setJobTitle(user.getJobTitle());
            userEntity.setContacts(user.getContacts());
            userRepository.save(userEntity);
        }
    }

    public void save(User user) {
        user.getSecurityUser().setRoles(Set.of(Role.USER));
        user.getSecurityUser().setActive(true);
        userRepository.save(user);
    }
}
