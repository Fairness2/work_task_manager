package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    /**
     * Дефолтное кол-во на странице
     */
    private Integer perPage = 10;

    /**
     * Получение
     * @param page
     * @param specification
     * @return Page<Task>
     */
    public Page<Task> getTasks(int page, Specification<Task> specification) {
        return repository.findAll(specification, PageRequest.of(page - 1, perPage));
    }
}
