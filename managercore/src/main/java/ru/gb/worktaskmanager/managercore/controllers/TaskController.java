package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.worktaskmanager.managercore.converters.TaskEditConverter;
import ru.gb.worktaskmanager.managercore.dtos.*;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.mappers.TaskMapper;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;
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
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;
    private final TaskEditConverter taskEditConverter;

    @Operation(
            summary = "Получение списка всех заданий для конкретного пользователя",
            responses = {
                    @ApiResponse(
                            description = "Список получен", responseCode = "200"
                    )
            }
    )
    @GetMapping()
    public TaskListDto getTasks(@RequestParam (name = "pageIndex", defaultValue = "1")  @Parameter(description = "Номер страницы", required = true) Integer pageIndex,
                                @RequestParam (name = "userId", defaultValue = "1")  @Parameter(description = "id пользователя", required = true) Long userId) {
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setUserId(userId);
        requestDto.setPage(pageIndex);
        Specification<Task> specification = TaskSpecifications.build(requestDto);
        int page = requestDto.getPage() == null ? 1 : requestDto.getPage();
        Page<Task> taskPage = service.getTasks(page, specification);
        List<TaskDto> taskDtos = taskPage.getContent()
                .stream()
                .map(task -> (new TaskMapper()).map(task))
                .collect(Collectors.toList());

        return new TaskListDto(taskDtos, page, taskPage.getTotalPages());
    }

    @Operation(
            summary = "Получение списка всех заданий",
            responses = {
                    @ApiResponse(
                            description = "Список получен", responseCode = "200"
                    )
            }
    )
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

    @Operation(
            summary = "Получение задания по id",
            responses = {
                    @ApiResponse(
                            description = "Задание получено", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{id}")
    public TaskDto getAllTasks(@PathVariable @Parameter(description = "id задания", required = true) Long id) {
        return (new TaskMapper()).map(service.getTaskById(id));
    }

    @Operation(
            summary = "Создание нового задания",
            responses = {
                    @ApiResponse(
                            description = "Задание создано", responseCode = "201"
                    )
            }
    )
    @PostMapping()
    public TaskDto createTask(@RequestBody @Valid RequestCreateTaskDto createTaskDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        Task newTask = service.createTask(createTaskDto);

        return (new TaskMapper()).map(newTask);
    }

    @Operation(
            summary = "Удаление задания по id",
            responses = {
                    @ApiResponse(
                            description = "Задание удалено", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public void removeTask(@PathVariable @Parameter(description = "id задания", required = true) Long id){
        service.removeTaskById(id);
    }

    @Operation(
            summary = "Редактиование задания",
            responses = {
                    @ApiResponse(
                            description = "Задание отредактировано", responseCode = "200"
                    )
            }
    )
    @PatchMapping
    public void editTask(@RequestBody @Valid RequestEditTaskDto editTaskDto) {
        Task editedTask = service.getTaskById(editTaskDto.getId());
        service.editTask(taskEditConverter.editTaskFromDto(editedTask, editTaskDto));
    }

}
