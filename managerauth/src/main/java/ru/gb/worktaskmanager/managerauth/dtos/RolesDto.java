package ru.gb.worktaskmanager.managerauth.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class RolesDto {
    private String code;
    private String title;
}
