package ru.gb.worktaskmanager.managercore.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.worktaskmanager.managercore.dtos.*;
import ru.gb.worktaskmanager.managercore.entites.Comment;
import ru.gb.worktaskmanager.managercore.mappers.CommentMapper;
import ru.gb.worktaskmanager.managercore.repositories.specifications.CommentSpecifications;
import ru.gb.worktaskmanager.managercore.services.CommentService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Контроллер комментариев
 */
@RestController
@RequestMapping("/comments")
@Validated
public class CommentController {
    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping()
    public CommentListResponseDto getComments(@RequestParam (name = "page", defaultValue = "1")  @Parameter(description = "Номер страницы", required = true) Integer pageIndex,
                                              @RequestParam (name = "taskId", defaultValue = "1")  @Parameter(description = "id пользователя", required = true) Long taskId) {
        //TODO по текущему пользователю
        //TODO описание свагера
        Specification<Comment> specification = CommentSpecifications.build(taskId, pageIndex);
        int page = pageIndex == null ? 1 : pageIndex;
        Page<Comment> taskPage = service.getComments(page, specification);
        //TODO DTO mapper
        List<CommentResponseDto> commentDtos = taskPage.getContent()
                .stream()
                .map(comment -> (new CommentMapper()).map(comment))
                .collect(Collectors.toList());

        return new CommentListResponseDto(commentDtos, page, taskPage.getTotalPages());
    }

    @PostMapping()
    public CommentResponseDto createComment(@RequestBody @Valid RequestCreateCommentDto createCommentDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        //TODO описание свагера
        Comment newComment = service.createComment(createCommentDto);

        return (new CommentMapper()).map(newComment);
    }

    @PostMapping("/action")
    public boolean actionComment(@RequestBody @Valid ActionRequestDto requestDto) {
        //TODO Создание задания с подстановкой текущего пользователя
        //TODO описание свагера

        return service.actionComment(requestDto.getAction(), requestDto.getCommentId());
    }
}
