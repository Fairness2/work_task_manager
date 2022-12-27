package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.worktaskmanager.managercore.dtos.*;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.mappers.TaskMapper;
import ru.gb.worktaskmanager.managercore.repositories.specifications.TaskSpecifications;
import ru.gb.worktaskmanager.managercore.services.TaskService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Контроллер заданий
 */
@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping()
    public TaskListDto getTasks(@RequestBody @Valid TaskRequestDto requestDto) {
        //TODO по текущему пользователю
        //TODO описание свагера
        Specification<Task> specification = TaskSpecifications.build(requestDto);
        int page = requestDto.getPage() == null ? 1 : requestDto.getPage();
        Page<Task> taskPage = service.getTasks(page, specification);
        //TODO DTO mapper
        List<TaskDto> taskDtos = taskPage.getContent()
                .stream()
                .map(task -> (new TaskMapper()).map(task))
                .collect(Collectors.toList());

        return new TaskListDto(taskDtos, page, taskPage.getTotalPages());
    }

    @GetMapping("/get-all")
    public TaskListDto getAllTasks(@RequestParam (name = "pageIndex", defaultValue = "1")  @Parameter(description = "Номер страницы", required = true) Integer pageIndex) {
        int page = pageIndex == null ? 1 : pageIndex;
        Specification<Task> specification = Specification.where(null);
        Page<Task> taskPage = service.getTasks(page, specification);
        List<TaskDto> taskDtos = taskPage.getContent()
                .stream()
                .map(task -> (new TaskMapper()).map(task))
                .collect(Collectors.toList());

        return new TaskListDto(taskDtos, page, taskPage.getTotalPages());
    }

    @PostMapping()
    public TaskDto createTask(@RequestBody @Valid RequestCreateTaskDto createTaskDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        //TODO описание свагера
        Task newTask = service.createTask(createTaskDto);

        return (new TaskMapper()).map(newTask);
    }
}
