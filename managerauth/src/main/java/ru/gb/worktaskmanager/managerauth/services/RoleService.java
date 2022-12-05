package ru.gb.worktaskmanager.managerauth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managerauth.entities.Role;
import ru.gb.worktaskmanager.managerauth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByTitle("ADMIN").get();
    }
}