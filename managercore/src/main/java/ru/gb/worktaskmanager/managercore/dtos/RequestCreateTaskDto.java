package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.DateGreaterOrEqualsThan;
import validation.intefaces.DateGreaterOrEqualsThanNow;
import validation.intefaces.UserExist;
import validation.intefaces.UserWithRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@DateGreaterOrEqualsThan.List({
        @DateGreaterOrEqualsThan(
                field = "planEndDate",
                secondField = "planStartDate",
                message = "Плановая дата начала не может быть меньше плановой даты завершения"
        )
})
public class RequestCreateTaskDto {
    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 2, max = 255, message = "Заголовок должен содержать от 2-х до 255-и символов")
    private String title;

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 2, message = "Описание должно содержать от 2-х символов")
    private String description;

    @NotNull(message = "Работник не может быть пустым")
    @UserExist(message = "Такого работника не существует")
    @UserWithRole(role = "employer", message = "Работник не заявлен как работник")
    private Long employerId;

    @NotNull(message = "Автор не может быть пустым")
    @UserExist(message = "Автора не существует")
    @UserWithRole(role = "director", message = "Автор не заявлен как руководитель")
    private Long authorId; // TODO подставлять из принципала

    @NotNull(message = "Ответственный не может быть пустым")
    @UserExist(message = "Ответственного не существует")
    @UserWithRole(role = "employer", message = "Ответственный не заявлен как работник")
    private Long responsibleUserId;

    @NotNull(message = "Число рабочих часов должно быть заполнено")
    @Positive(message = "Число рабочих часов должно быть больше 0")
    private Integer workingHours;

    @NotNull(message = "Планируемая дата начала должна быть заполнена")
    @DateGreaterOrEqualsThanNow(message = "Планируемая дата начала должна быть больше текущей даты")
    private LocalDateTime planStartDate;

    @NotNull(message = "Планируемая дата окончкания должна быть заполнена")
    @DateGreaterOrEqualsThanNow(message = "Планируемая дата окончкания должна быть больше текущей даты")
    private LocalDateTime planEndDate;

}
