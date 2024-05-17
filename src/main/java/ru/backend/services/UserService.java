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

    public void fillUserEntity(User user, User newUserData) {
        if (newUserData.getSecurityUser() != null) {
            if (newUserData.getSecurityUser().getPassword() != null) {
                user.getSecurityUser()
                        .setPassword(passwordEncoder
                                .encode(newUserData.getSecurityUser().getPassword()));
            }
            if (newUserData.getSecurityUser().getLogin() != null) {
                user.getSecurityUser().setLogin(newUserData.getSecurityUser().getLogin());
            }
        }
        if (newUserData.getName() != null) {user.setName(newUserData.getName());}
        if (newUserData.getSurname() != null) {user.setSurname(newUserData.getSurname());}
        if (newUserData.getJobTitle() != null) {user.setJobTitle(newUserData.getJobTitle());}
        if (newUserData.getContacts() != null) {user.setContacts(newUserData.getContacts());}
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
