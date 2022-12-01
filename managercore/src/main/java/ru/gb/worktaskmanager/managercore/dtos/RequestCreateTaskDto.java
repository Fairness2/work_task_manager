package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import validation.intefaces.DateFormat;
import validation.intefaces.StringDateGreaterOrEqualsThan;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@StringDateGreaterOrEqualsThan.List({
        @StringDateGreaterOrEqualsThan(
                field = "dateTo",
                secondField = "dateFrom",
                message = "Дата \"до\" не может быть меньше даты \"от\""
        )
})
public class RequestCreateTaskDto {
    @Size(min = 2, max = 255, message = "Заголовок должнен содержать от 2-х до 255-и символов")
    @NotBlank(message = "Заголовок не может быть пустым")
    private String title;

    @Size(min = 2, max = 255, message = "Заголовок должнен содержать от 2-х до 255-и символов")
    @NotBlank(message = "Заголовок не может быть пустым")
    private String description;

    @UserExist //TODO валидатор
    @WorkerExist //TODO валидатор
    @NotNull(message = "Работник не может быть пустым")
    private Integer employer_id;

    @UserExist //TODO валидатор
    @BossExist //TODO валидатор
    @NotNull(message = "Автор не может быть пустым")
    private Integer author_id; // TODO подставлять из принципала

    @UserExist //TODO валидатор
    @WorkerExist //TODO валидатор
    @NotNull(message = "Ответственный не может быть пустым")
    private Integer responsible_user_id;

    @Positive
    @NotNull(message = "Ответственный не может быть пустым")
    private Integer working_hours;

    @DateFormat(format = "yyyy-MM-dd kk:mm")
    @StringDateBigerThanNow //TODO валидатор
    @NotNull(message = "Автор не может быть пустым")
    private String plan_start_date;

    @DateFormat(format = "yyyy-MM-dd kk:mm")
    @StringDateBigerThanNow //TODO валидатор
    @NotNull(message = "Автор не может быть пустым")
    private String plan_end_date;

}
