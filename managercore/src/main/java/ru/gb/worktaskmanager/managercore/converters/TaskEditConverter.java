package ru.gb.worktaskmanager.managercore.converters;

import lombok.RequiredArgsConstructor;
import lombok.With;
import org.springframework.stereotype.Component;
import ru.gb.worktaskmanager.managercore.dtos.RequestEditTaskDto;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.services.UserService;

@Component
@RequiredArgsConstructor
public class TaskEditConverter {
    private final UserService userService;
    public Task editTaskFromDto(Task task, RequestEditTaskDto editTaskDto) {
        task.setTitle(editTaskDto.getTitle());
        task.setDescription(editTaskDto.getDescription());
        task.setEmployerId(userService.findUserById(editTaskDto.getEmployerId()));
        task.setResponsibleUserId(userService.findUserById(editTaskDto.getResponsibleUserId()));
        task.setWorkingHours(editTaskDto.getWorkingHours());
        task.setPlanStartDate(editTaskDto.getPlanStartDate());
        task.setPlanEndDate(editTaskDto.getPlanEndDate());
        return task;
    }
}
