package ru.gb.worktaskmanager.managercore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private UserDto employer;

    private UserDto author;

    private UserDto responsibleUser;

    private Integer workingHours;

    private String planStartDate;

    private String planEndDate;

    private String createdAt;

    private String updatedAt;

    private List<RefTaskStatusDto> historyStatus;

    private List<FileDto> files;

}
