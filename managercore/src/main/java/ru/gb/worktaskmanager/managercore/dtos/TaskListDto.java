package ru.gb.worktaskmanager.managercore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TaskListDto {
    private List<TaskDto> tasks;
    private int page;
    private int total;

    public TaskListDto(List<TaskDto> tasks, int page, int total) {
        this.tasks = tasks;
        this.page = page;
        this.total = total;
    }
}
