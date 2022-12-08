package ru.gb.worktaskmanager.managerauth.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Schema(description = "Модель запроса данных пользователя для авторизации")
public class JwtRequest {
    @Schema(description = "Имя пользователя", required = true, example = "user")
    private String username;
    @Schema(description = "Пароль пользователя", required = true, example = "12345")
    private String password;
}