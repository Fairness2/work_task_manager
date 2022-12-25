package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.dtos.RequestCreateCommentDto;
import ru.gb.worktaskmanager.managercore.entites.Comment;
import ru.gb.worktaskmanager.managercore.entites.CommentType;
import ru.gb.worktaskmanager.managercore.entites.Task;
import ru.gb.worktaskmanager.managercore.repositories.CommentRepository;
import ru.gb.worktaskmanager.managercore.repositories.CommentTypeRepository;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;

/**
 * Сервис комментариев к заданиям
 */
@Service
public class CommentService {
    private final CommentRepository repository;
    private final CommentTypeRepository typeRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public CommentService(CommentRepository repository, CommentTypeRepository typeRepository, TaskRepository taskRepository) {
        this.repository = repository;
        this.typeRepository = typeRepository;
        this.taskRepository = taskRepository;
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
        CommentType type = typeRepository.getReferenceById(commentDto.getType());
        Task task = taskRepository.getReferenceById(commentDto.getTaskId());

        Comment comment = Comment.builder()
                .text(commentDto.getText())
                .authorId(commentDto.getAuthorId()) // TODO из авторизованного пользователя
                .task(task)
                .type(type)
                .build();

        comment = repository.save(comment);

        return comment;
    }
}
