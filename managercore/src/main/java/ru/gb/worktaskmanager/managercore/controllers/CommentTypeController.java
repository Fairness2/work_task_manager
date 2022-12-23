package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.worktaskmanager.managercore.dtos.CommentTypeListResponseDto;
import ru.gb.worktaskmanager.managercore.dtos.CommentTypeResponseDto;
import ru.gb.worktaskmanager.managercore.entites.CommentType;
import ru.gb.worktaskmanager.managercore.services.CommentTypeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Контроллер типов комментариев
 */
@RestController
@RequestMapping("/comment-types")
public class CommentTypeController {
    private final CommentTypeService service;

    @Autowired
    public CommentTypeController(CommentTypeService service) {
        this.service = service;
    }

    @Operation(
            summary = "Получение всех типов комментариев",
            responses = {
                    @ApiResponse(
                            description = "Получение всех типов комментариев",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CommentTypeListResponseDto.class))
                    )
            }
    )
    /**
     * Получение полного списка типов комментариев
     */
    @GetMapping()
    public CommentTypeListResponseDto getStatuses() {
        List<CommentType> statuses = service.getCommentTypes();

        return new CommentTypeListResponseDto(statuses
                .stream()
                .map(status -> new CommentTypeResponseDto(status.getCode(), status.getTitle()))
                .collect(Collectors.toList())
        );
    }
}
