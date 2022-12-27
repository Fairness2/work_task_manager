package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.worktaskmanager.managercore.entites.ActionEnum;
import validation.intefaces.OpenCommentExist;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ActionRequestDto {
    @NotNull(message = "Действие не должно быть пустым")
    private ActionEnum action;
    @NotNull(message = "Комментарий не должен быть пустым")
    @Positive(message = "Идентификатор комментария не может быть меньше нуля")
    @OpenCommentExist
    private Long commentId;
}
