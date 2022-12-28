package ru.gb.worktaskmanager.managercore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.worktaskmanager.managercore.entites.Users;
import ru.gb.worktaskmanager.managercore.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<Users> findAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return usersList;
    }
    public Users findUserByUserName(String username){
        return userRepository.findByUsername(username).get();
    }

    public Users findUserById(Long id) {
        return userRepository.findById(id).get();
    }
}