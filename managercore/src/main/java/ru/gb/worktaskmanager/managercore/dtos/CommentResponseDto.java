package ru.gb.worktaskmanager.managercore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;

    private String text;

    private UserDto author;

    private String createdAt;

    private String updatedAt;

    private Long taskId;

    private CommentTypeResponseDto type;

    private String resultAt;

    private String action;
}
