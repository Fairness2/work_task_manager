package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.UserExist;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    @Positive
    private Integer page;
    @Positive
    @UserExist
    private Long taskId;

    //TODO остальные параметры фильтрации
}
