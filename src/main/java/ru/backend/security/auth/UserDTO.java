package ru.backend.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String login;
    private String password;
    private String name;
    private String surname;
    private String jobTitle;
    private String contacts;
}
