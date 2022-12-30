package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.TaskExist;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    @Positive
    private Integer page;
    @Positive
    @TaskExist
    private Long taskId;

    //TODO остальные параметры фильтрации
}
