package ru.gb.worktaskmanager.managerauth.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
}
