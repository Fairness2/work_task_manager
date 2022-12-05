package ru.gb.worktaskmanager.managercore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefTaskStatusDto {
    private Long id;
    private Long taskId;
    private TaskStatusDto status;
    private String createdAt;
    private String endedAt;
}
