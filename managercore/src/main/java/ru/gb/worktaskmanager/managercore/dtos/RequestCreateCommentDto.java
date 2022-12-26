package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RequestCreateCommentDto {
    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(min = 2, max = 255, message = "Текст комментария должен содержать от 2-х до 255-и символов")
    private String text;

    @NotNull(message = "Автор не может быть пустым")
    @UserExist(message = "Автора не существует")
    @UserWithRole(role = "director", message = "Автор не заявлен как руководитель")
    private Long authorId; // TODO подставлять из принципала

    @NotBlank(message = "Текст комментария не может быть пустым")
    @CommentTypeExist
    private String type;

    @NotNull(message = "Задание не может быть пустым")
    @Positive(message = "Идентификатор задания недействителен")
    @TaskExist
    private Long taskId;
}
