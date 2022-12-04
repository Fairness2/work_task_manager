package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.DateFormat;
import validation.intefaces.StringDateGreaterOrEqualsThan;
import validation.intefaces.UserExist;
import validation.intefaces.UserWithRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@StringDateGreaterOrEqualsThan.List({
        @StringDateGreaterOrEqualsThan(
                field = "planEndDate",
                secondField = "planStartDate",
                message = "Плановая дата начала не может быть меньше плановой даты завершения"
        )
})
public class RequestCreateTaskDto {
    @Size(min = 2, max = 255, message = "Заголовок должнен содержать от 2-х до 255-и символов")
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @Size(min = 2, message = "Описание должно содержать от 2-х символов")
    @NotBlank(message = "Заголовок не может быть пустым")
    private String description;

    @UserExist(message = "Такого работника не существует")
    @UserWithRole(role = "employer", message = "Работник не заявлен как работник")
    @NotNull(message = "Работник не может быть пустым")
    private Integer employerId;

    @UserExist(message = "Автора не существует")
    @UserWithRole(role = "director", message = "Автор не заявлен как руководитель")
    @NotNull(message = "Автор не может быть пустым")
    private Integer authorId; // TODO подставлять из принципала

    @UserExist(message = "Ответственного не существует")
    @UserWithRole(role = "employer", message = "Ответственный не заявлен как работник")
    @NotNull(message = "Ответственный не может быть пустым")
    private Integer responsibleUserId;

    @Positive
    @NotNull(message = "Число рабочих числов должно быть заполнено")
    private Integer workingHours;

    @DateFormat(format = "yyyy-MM-dd kk:mm")
    @StringDateGreaterOrEqualsThan(format = "yyyy-MM-dd kk:mm", message = "Планируемая дата начала должна быть больше текущей даты")
    @NotBlank(message = "Планируемая дата начала должна быть заполнена")
    private String planStartDate;

    @DateFormat(format = "yyyy-MM-dd kk:mm")
    @StringDateGreaterOrEqualsThan(format = "yyyy-MM-dd kk:mm", message = "Планируемая дата окончкания должна быть больше текущей даты")
    @NotBlank(message = "Планируемая дата окончкания должна быть заполнена")
    private String planEndDate;

}
