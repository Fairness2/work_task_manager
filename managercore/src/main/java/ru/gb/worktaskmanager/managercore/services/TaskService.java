package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;

@Service
public class TaskService {
    private TaskRepository repository;

    @Autowired
    private void setTaskRepository(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }


}
