package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;

import java.util.Date;

/**
 * Стандартная дто для ошибки сервисов
 */
@Data
public class ProjectError {
    private int status;
    private String message;
    private Date timestamp;

    public ProjectError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
