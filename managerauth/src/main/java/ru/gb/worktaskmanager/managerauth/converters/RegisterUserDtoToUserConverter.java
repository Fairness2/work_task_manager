package ru.gb.worktaskmanager.managerauth.converters;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.worktaskmanager.managerauth.dtos.RegisterUserDto;
import ru.gb.worktaskmanager.managerauth.entities.Users;

@Component
@NoArgsConstructor
public class RegisterUserDtoToUserConverter {

    public Users registerUserDtoConvertToUser(RegisterUserDto registerUserDto) {
        Users user = new Users();
        user.setId(null);
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setName(registerUserDto.getName());
        user.setPatronymic(registerUserDto.getPatronymic());
        return user;
    }
}
