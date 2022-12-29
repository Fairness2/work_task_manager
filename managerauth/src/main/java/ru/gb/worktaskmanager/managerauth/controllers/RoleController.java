package ru.gb.worktaskmanager.managerauth.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managerauth.converters.RoleToDtoConverter;
import ru.gb.worktaskmanager.managerauth.dtos.RolesListDto;
import ru.gb.worktaskmanager.managerauth.services.RoleService;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Получение ролей", description = "Методы работы с ролями пользователей")
public class RoleController {
    private final RoleService roleService;
    private final RoleToDtoConverter roleToDtoConverter;

    @GetMapping("/find-all")
    public RolesListDto findAllRoles() {
        return roleToDtoConverter.rolesListConvertToDto(roleService.findAllRoles());
    }
}
