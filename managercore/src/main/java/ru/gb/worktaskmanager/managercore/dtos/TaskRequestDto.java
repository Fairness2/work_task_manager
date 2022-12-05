package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.UserExist;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class TaskRequestDto {
    @Positive
    private Integer page;
    @Positive
    @UserExist
    private Integer userId;

    //TODO остальные параметры фильтрации
}
