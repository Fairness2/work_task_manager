package ru.gb.worktaskmanager.managerauth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managerauth.entities.Roles;
import ru.gb.worktaskmanager.managerauth.repositories.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Roles findRoleByCode(String code) {
       return roleRepository.findByCode(code).get();
    }

    public List<Roles> findAllRoles() {
        return roleRepository.findAll();
    }
}