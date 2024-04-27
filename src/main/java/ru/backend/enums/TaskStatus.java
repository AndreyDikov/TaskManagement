package ru.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskStatus {
    UPCOMING("предстоящая"),
    COMPLETED("выполненная"),
    FAILED("проваленная");

    private final String status;
}
