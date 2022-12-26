package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CommentListResponseDto {
    private List<CommentResponseDto> comments;
    private int page;
    private int total;

    public CommentListResponseDto(List<CommentResponseDto> comments, int page, int total) {
        this.comments = comments;
        this.page = page;
        this.total = total;
    }
}
