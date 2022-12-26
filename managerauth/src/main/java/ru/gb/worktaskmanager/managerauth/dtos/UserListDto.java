package ru.gb.worktaskmanager.managerauth.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class UserListDto {
    private List<UserDto> userList;
}
