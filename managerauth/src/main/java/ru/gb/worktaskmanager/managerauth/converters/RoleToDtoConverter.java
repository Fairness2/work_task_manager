package ru.gb.worktaskmanager.managerauth.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.worktaskmanager.managerauth.dtos.RolesDto;
import ru.gb.worktaskmanager.managerauth.dtos.RolesListDto;
import ru.gb.worktaskmanager.managerauth.dtos.UserDto;
import ru.gb.worktaskmanager.managerauth.dtos.UserListDto;
import ru.gb.worktaskmanager.managerauth.entities.Roles;
import ru.gb.worktaskmanager.managerauth.entities.Users;
import ru.gb.worktaskmanager.managerauth.services.RoleService;
import ru.gb.worktaskmanager.managerauth.services.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleToDtoConverter {
    private final RoleService roleService;

    public RolesDto roleConvertToDto(Roles roles) {
        RolesDto rolesDto = new RolesDto();
        rolesDto.setCode(roles.getCode());
        rolesDto.setTitle(roles.getTitle());
        return rolesDto;
    }

    public RolesListDto rolesListConvertToDto(List<Roles> rolesList) {
        RolesListDto rolesListDto = new RolesListDto();
        for (Roles roles : rolesList) {
            RolesDto rolesDto = new RolesDto();
            rolesDto.setCode(roles.getCode());
            rolesDto.setTitle(roles.getTitle());
        }
        return rolesListDto;
    }
}
