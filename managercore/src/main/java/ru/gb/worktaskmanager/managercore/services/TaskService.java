package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.dtos.RequestCreateTaskDto;
import ru.gb.worktaskmanager.managercore.entites.RefTaskStatus;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.entites.TaskStatus;
import ru.gb.worktaskmanager.managercore.entites.TaskStatusEnum;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

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
     * Получение заданий
     * @param page
     * @param specification
     * @return Page<Task>
     */
    public Page<Task> getTasks(int page, Specification<Task> specification) {
        return repository.findAll(specification, PageRequest.of(page - 1, perPage));
    }

    /**
     * Создание задания
     * @param taskDto
     * @return Task
     */
    public Task createTask(RequestCreateTaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .description(taskDto.getDescription())
                .employerId(taskDto.getEmployerId())
                .authorId(taskDto.getAuthorId()) // TODO из авторизованного пользователя
                .responsibleUserId(taskDto.getResponsibleUserId())
                .workingHours(taskDto.getWorkingHours())
                .planStartDate(taskDto.getPlanStartDate())
                .planEndDate(taskDto.getPlanEndDate())
                .build();

        RefTaskStatus taskStatus = RefTaskStatus.builder()
                .status(new TaskStatus(TaskStatusEnum.pending.name(), null))
                .task(task)
                .userId(task.getAuthorId())
                .build();

        task.setTaskStatuses(Arrays.stream(new RefTaskStatus[]{taskStatus})
                .collect(Collectors.toList()));

        task = repository.save(task);

        return task;
    }
}
