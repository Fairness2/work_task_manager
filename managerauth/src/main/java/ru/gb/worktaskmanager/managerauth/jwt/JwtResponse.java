package ru.gb.worktaskmanager.managerauth.jwt;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель ответа авторизации")
public class JwtResponse {
    @Schema(description = "Возвращенный токен", required = true)
    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
