package ru.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.backend.enums.Role;
import ru.backend.enums.UserStatus;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String login;

    private String password;

    private String name;

    private String surname;

    private String jobTitle;

    private String contacts;

    @OneToMany(mappedBy = "fromUser")
    private List<Task> assignedTasks;

    @OneToMany(mappedBy = "toUser")
    private List<Task> createdTasks;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;
}
