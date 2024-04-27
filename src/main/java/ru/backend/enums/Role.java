package ru.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("администратор"),
    USER("пользователь");

    private final String role;
}
