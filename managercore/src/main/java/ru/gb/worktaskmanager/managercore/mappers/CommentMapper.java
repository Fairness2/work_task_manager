package ru.gb.worktaskmanager.managercore.mappers;

import ru.gb.worktaskmanager.managercore.dtos.CommentResponseDto;
import ru.gb.worktaskmanager.managercore.dtos.CommentTypeResponseDto;
import ru.gb.worktaskmanager.managercore.entites.Comment;

public class CommentMapper implements Mapper<Comment, CommentResponseDto> {
    @Override
    public CommentResponseDto map(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .author(comment.getAuthor())
                .createdAt(comment.getCreatedAt().toString())
                .updatedAt(comment.getUpdatedAt().toString())
                .type(new CommentTypeResponseDto(comment.getType().getCode(), comment.getType().getTitle()))
                .taskId(comment.getTaskId())
                .resultAt(comment.getResultAt() != null ? comment.getResultAt().toString() : null)
                .action(comment.getAction() != null ? comment.getAction().name() : null)
                .build();
    }
}
