package ru.gb.worktaskmanager.managerauth.jwt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Модель ответа авторизации")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    @Schema(description = "Возвращенный токен", required = true)
    private String token;
    private Long id;
}
