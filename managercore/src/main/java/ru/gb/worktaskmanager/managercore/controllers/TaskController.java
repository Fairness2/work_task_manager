package ru.gb.worktaskmanager.managercore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managercore.dtos.TaskDto;
import ru.gb.worktaskmanager.managercore.services.TaskService;


/**
 * Контроллер заданий
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService service;

    @Autowired
    private void setTaskService(TaskService service) {
        this.service = service;
    }


    @PostMapping()
    public TaskDto createTask() {

    }
}
