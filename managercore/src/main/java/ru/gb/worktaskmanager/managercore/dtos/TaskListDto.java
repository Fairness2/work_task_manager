package ru.gb.worktaskmanager.managercore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskListDto {
    private List<TaskDto> tasks;
    private int page;
    private int total;
}
