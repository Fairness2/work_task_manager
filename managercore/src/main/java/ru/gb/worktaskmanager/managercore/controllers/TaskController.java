package ru.gb.worktaskmanager.managercore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.worktaskmanager.managercore.dtos.*;
import ru.gb.worktaskmanager.managercore.entites.Task;
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
    private TaskService service;

    @Autowired
    private void setTaskService(TaskService service) {
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
                .map(task -> TaskDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .employer(task.getEmployer())
                        .author(task.getAuthor())
                        .responsibleUser(task.getResponsibleUser())
                        .workingHours(task.getWorkingHours())
                        .planStartDate(task.getStrPlanStartDate())
                        .planEndDate(task.getStrPlanEndDate())
                        .createdAt(task.getStrCreatedAt())
                        .updatedAt(task.getStrUpdatedAt())

                        .historyStatus(task.getTaskStatuses().stream().map(status -> RefTaskStatusDto.builder()
                                .taskId(task.getId())
                                .status(new TaskStatusDto(status.getStatus().getCode(), status.getStatus().getTitle()))
                                .createdAt(status.getStrCreatedAt())
                                .endedAt(status.getStrEndedAt())
                                .build())
                                .collect(Collectors.toList()))

                        .files(task.getFiles().stream().map(file -> FileDto.builder()
                                        .taskId(task.getId())
                                        .name(file.getName())
                                        .type(file.getType())
                                        .link(file.getLink())
                                .build())
                                .collect(Collectors.toList()))

                        .build())
                .collect(Collectors.toList());

        return new TaskListDto(taskDtos, page, taskPage.getTotalPages());
    }

    @PostMapping()
    public TaskDto createTask(@RequestBody @Valid RequestCreateTaskDto createTaskDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        //TODO описание свагера

        return new TaskDto();
    }
}
