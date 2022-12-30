package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ДТО для ответа список типов комментариев
 */
@Data
@NoArgsConstructor
public class CommentTypeListResponseDto {
    List<CommentTypeResponseDto> list;

    public CommentTypeListResponseDto(List<CommentTypeResponseDto> list) {
        this.list = list;
    }
}
