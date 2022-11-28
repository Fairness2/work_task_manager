package ru.gb.worktaskmanager.managerauth.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.worktaskmanager.managerauth.dtos.UserDto;
import ru.gb.worktaskmanager.managerauth.entities.User;
import ru.gb.worktaskmanager.managerauth.services.UserService;

@Component
@RequiredArgsConstructor
public class UserToDtoConverter {
    private final UserService userService;

    public UserDto userConvertToDtoByName(String username) {
        User user = userService.findByUsername(username).get();
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setSurname(user.getSurname());
        return userDto;
    }

    public UserDto userConvertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setSurname(user.getSurname());
        return userDto;
    }
}
