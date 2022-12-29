package ru.gb.worktaskmanager.managerauth.converters;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.worktaskmanager.managerauth.dtos.RegisterUserDto;
import ru.gb.worktaskmanager.managerauth.entities.Roles;
import ru.gb.worktaskmanager.managerauth.entities.Users;
import ru.gb.worktaskmanager.managerauth.services.RoleService;

@Component
@RequiredArgsConstructor
public class RegisterUserDtoToUserConverter {
    private final RoleService roleService;

    public Users registerUserDtoConvertToUser(RegisterUserDto registerUserDto) {
        Users user = new Users();
        user.setUsername(registerUserDto.getUsername());
        user.setPassword(registerUserDto.getPassword());
        user.setName(registerUserDto.getName());
        user.setPatronymic(registerUserDto.getPatronymic());
//        Roles role = roleService.getRole("USER");
//        user.getRoles().add(role);
        return user;
    }
}
