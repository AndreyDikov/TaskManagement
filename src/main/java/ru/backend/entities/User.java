package ru.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.backend.annotations.Hint;
import ru.backend.security.user.SecurityUser;

import java.util.List;

@Getter
@Setter
@ToString(exclude = {
        "securityUser"
        , "assignedTasks"
        , "createdTasks"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hint("Элементарные типы использовать предпочтительнее")
    private long id;
    private String name;

    private String surname;

    private String jobTitle;

    private String contacts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "security_id")
    private SecurityUser securityUser;

    @OneToMany(mappedBy = "fromUser")
    private List<Task> assignedTasks;

    @OneToMany(mappedBy = "toUser")
    private List<Task> createdTasks;
}
