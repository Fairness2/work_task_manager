package ru.gb.worktaskmanager.managerauth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managerauth.converters.RegisterUserDtoToUserConverter;
import ru.gb.worktaskmanager.managerauth.dtos.RegisterUserDto;
import ru.gb.worktaskmanager.managerauth.services.UserService;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Регистрация", description = "Методы регистрации нового пользователя")
public class RegisterController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RegisterUserDtoToUserConverter registerUserDtoToUserConverter;

    @Operation(
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping("/register")
    public void registerNewUser(@RequestBody RegisterUserDto registerUserDto) {
        String bcryptCachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
        registerUserDto.setPassword(bcryptCachedPassword);
        registerUserDto.setConfirmPassword(bcryptCachedPassword);
        userService.createNewUser(registerUserDtoToUserConverter.registerUserDtoConvertToUser(registerUserDto));
    }
}