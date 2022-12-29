package ru.gb.worktaskmanager.managercore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.entites.Roles;
import ru.gb.worktaskmanager.managercore.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Roles getUserRole() {
        return roleRepository.findByTitle("ADMIN").get();
    }
}