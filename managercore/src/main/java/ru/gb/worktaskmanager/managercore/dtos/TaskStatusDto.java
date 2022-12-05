package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskStatusDto {
    private String code;
    private String title;

    public TaskStatusDto(String code, String title) {
        this.code = code;
        this.title = title;
    }
}
