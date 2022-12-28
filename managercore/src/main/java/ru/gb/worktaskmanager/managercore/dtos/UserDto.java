package ru.gb.worktaskmanager.managercore.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String patronymic;
}
