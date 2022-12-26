package ru.gb.worktaskmanager.managercore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для ответа тип комментария
 */
@Data
@NoArgsConstructor
public class CommentTypeResponseDto {
    private String code;
    private String title;

    public CommentTypeResponseDto(String code, String title) {
        this.code = code;
        this.title = title;
    }
}
