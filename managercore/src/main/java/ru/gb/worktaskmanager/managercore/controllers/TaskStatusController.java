package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managercore.dtos.TaskStatusDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskStatusListDto;
import ru.gb.worktaskmanager.managercore.entites.TaskStatus;
import ru.gb.worktaskmanager.managercore.services.TaskStatusService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер статусов заданий
 */
@RestController
@RequestMapping("/task-statuses")
public class TaskStatusController {
    private final TaskStatusService service;

    @Autowired
    public TaskStatusController(TaskStatusService service) {
        this.service = service;
    }

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

    /**
     * Получение полного списка стаутусов
     */
    @GetMapping()
    public TaskStatusListDto getStatuses() {
        List<TaskStatus> statuses = service.getTaskStatuses();

        return new TaskStatusListDto(statuses
                .stream()
                .map(status -> new TaskStatusDto(status.getCode(), status.getTitle()))
                .collect(Collectors.toList())
        );
    }
}
