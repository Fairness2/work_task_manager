package ru.gb.worktaskmanager.managerauth.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class UserListDto {
    private List<UserDto> userList;

    public UserListDto() {
        this.userList = new ArrayList<>();
    }
}
