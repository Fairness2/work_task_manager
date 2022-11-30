package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managercore.dtos.TaskStatusListDto;
import ru.gb.worktaskmanager.managercore.services.TaskStatusService;

/**
 * Контроллер статусов заданий
 */
@RestController
@RequestMapping("/task_status")
public class TaskStatusController {
    private TaskStatusService service;

    @Operation(
            summary = "Получение всех статусов заданий",
            responses = {
                    @ApiResponse(
                            description = "Получение всех статусов",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TaskStatusListDto.class))
                    )
            }
    )
    @Autowired
    private void setTaskStatusService(TaskStatusService service) {
        this.service = service;
    }
}
