package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.dtos.RequestCreateCommentDto;
import ru.gb.worktaskmanager.managercore.entites.*;
import ru.gb.worktaskmanager.managercore.repositories.CommentRepository;

/**
 * Сервис комментариев к заданиям
 */
@Service
public class CommentService {
    private final CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    /**
     * Дефолтное кол-во на странице
     */
    private Integer perPage = 10;

    /**
     * Получение комментариев
     * @param page
     * @param specification
     * @return Page<Comment>
     */
    public Page<Comment> getComments(int page, Specification<Comment> specification) {
        return repository.findAll(specification, PageRequest.of(page - 1, perPage));
    }

    /**
     * Создание комментария
     * @param commentDto
     * @return Comment
     */
    public Comment createComment(RequestCreateCommentDto commentDto) {
        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .authorId(commentDto.getAuthorId()) // TODO из авторизованного пользователя
                .task(Task.builder()
                        .id(commentDto.getTaskId())
                        .build())
                .type(new CommentType(commentDto.getType(), null))
                .build();


        comment = repository.save(comment);

        return comment;
    }
}
