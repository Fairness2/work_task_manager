package ru.gb.worktaskmanager.managercore.mappers;

import ru.gb.worktaskmanager.managercore.dtos.FileDto;
import ru.gb.worktaskmanager.managercore.dtos.RefTaskStatusDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskDto;
import ru.gb.worktaskmanager.managercore.dtos.TaskStatusDto;
import ru.gb.worktaskmanager.managercore.entites.Task;

import java.util.stream.Collectors;

public class TaskMapper implements Mapper<Task, TaskDto> {
    @Override
    public TaskDto map(Task task) {
        return TaskDto.builder()
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

                .build();
    }
}
