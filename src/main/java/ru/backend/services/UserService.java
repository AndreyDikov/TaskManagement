package ru.backend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.backend.entities.User;
import ru.backend.repositories.UserRepository;
import ru.backend.security.user.Role;
import ru.backend.security.user.SecurityUser;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

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
            fillUserEntity(userEntity, user);
            userRepository.save(userEntity);
        }
    }

    public void fillUserEntity(User userEntity, User user) {
        userEntity.getSecurityUser()
                .setPassword(passwordEncoder
                        .encode(user.getSecurityUser().getPassword()));
        userEntity.getSecurityUser().setLogin(user.getSecurityUser().getLogin());
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setJobTitle(user.getJobTitle());
        userEntity.setContacts(user.getContacts());
    }

    public void save(User user) {
        user.getSecurityUser()
                .setPassword(passwordEncoder
                        .encode(user.getSecurityUser().getPassword()));
        user.getSecurityUser().setRoles(Set.of(Role.USER));
        user.getSecurityUser().setActive(true);
        userRepository.save(user);
    }

    public User getUser(UserDetails userDetails) {
        return ((SecurityUser) userDetails).getUser();
    }

    public void updateUserStatus(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getSecurityUser().setActive(!user.getSecurityUser().isActive());
            userRepository.save(user);
        }
    }
}
