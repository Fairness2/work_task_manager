package ru.gb.worktaskmanager.managerauth.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class RolesListDto {
    private List<RolesDto> rolesList;

    public RolesListDto() {
        this.rolesList = new ArrayList<>();
    }
}
