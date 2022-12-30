package ru.gb.worktaskmanager.managercore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.entites.Users;
import ru.gb.worktaskmanager.managercore.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Users findUserById(Long id) {
        return userRepository.findById(id).get();
    }
}