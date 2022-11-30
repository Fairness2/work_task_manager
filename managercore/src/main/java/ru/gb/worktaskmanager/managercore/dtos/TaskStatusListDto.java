package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TaskStatusListDto {
    List<TaskStatusDto> list;

    public TaskStatusListDto(List<TaskStatusDto> list) {
        this.list = list;
    }
}
