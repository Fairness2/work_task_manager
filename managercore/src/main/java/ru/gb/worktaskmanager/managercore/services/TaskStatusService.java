package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.entites.TaskStatus;
import ru.gb.worktaskmanager.managercore.repositories.TaskStatusRepository;

import java.util.List;

@Service
public class TaskStatusService {
    private TaskStatusRepository repository;

    @Autowired
    private void setTaskRepository(TaskStatusRepository taskStatusRepository) {
        this.repository = taskStatusRepository;
    }

    /**
     * Получим все статусы заданий
     * @return List<TaskStatus>
     */
    public List<TaskStatus> getTaskStatuses() {
        return repository.findAll();
    }
}
