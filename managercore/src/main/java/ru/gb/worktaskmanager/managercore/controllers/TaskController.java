package ru.gb.worktaskmanager.managercore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.worktaskmanager.managercore.dtos.RequestCreateTaskDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskListDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskRequestDto;
import ru.gb.worktaskmanager.managercore.services.TaskService;

import javax.validation.Valid;


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

        return new TaskListDto();
    }

    @PostMapping()
    public TaskDto createTask(@RequestBody @Valid RequestCreateTaskDto createTaskDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        //TODO описание свагера

        return new TaskDto();
    }
}
