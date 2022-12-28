package ru.gb.worktaskmanager.managercore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.dtos.RequestCreateCommentDto;
import ru.gb.worktaskmanager.managercore.entites.*;
import ru.gb.worktaskmanager.managercore.repositories.*;

import java.time.LocalDateTime;

/**
 * Сервис комментариев к заданиям
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final CommentTypeRepository typeRepository;
    private final TaskRepository taskRepository;
    private final TaskStatusRepository statusRepository;
    private final RefTaskStatusRepository refTaskStatusRepository;
    private final UserService userService;

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
                .authorId(userService.findUserById(commentDto.getAuthorId())) // TODO из авторизованного пользователя
                .task(task)
                .type(type)
                .build();

        comment = repository.save(comment);

        return comment;
    }

    /**
     * Совершение действия с комментариев
     * @param action
     * @param commentId
     * @return boolean
     */
    public boolean actionComment(ActionEnum action, Long commentId) {
        switch (action) {
            case approve:
                return approveAction(commentId);
            case disprove:
                return disproveAction(commentId);
        }

        return false;
    }

    /**
     * Апрувим действие с задачей
     * @param commentId
     * @return boolean
     */
    private boolean approveAction(Long commentId) {
        Comment comment = repository.findById(commentId).orElseThrow(); // TODO ошибка ненахождения комментария
        comment.setResultAt(LocalDateTime.now());
        comment.setAction(ActionEnum.approve);
        RefTaskStatus taskStatus;
        if (comment.getType().getCode().equals(CommentTypeEnum.start_request.name())) {
            TaskStatus status = statusRepository.getReferenceById(TaskStatusEnum.pending.name());
            taskStatus = RefTaskStatus.builder()
                    .status(status)
                    .task(comment.getTask())
                    .userId(comment.getAuthorId().getId())
                    .build();
        } else if (comment.getType().getCode().equals(CommentTypeEnum.pause_request.name())) {
            TaskStatus status = statusRepository.getReferenceById(TaskStatusEnum.paused.name());
            taskStatus = RefTaskStatus.builder()
                    .status(status)
                    .task(comment.getTask())
                    .userId(comment.getAuthorId().getId())
                    .build();
        } else { // Значит завершение
            TaskStatus status = statusRepository.getReferenceById(TaskStatusEnum.done.name());
            taskStatus = RefTaskStatus.builder()
                    .status(status)
                    .task(comment.getTask())
                    .userId(comment.getAuthorId().getId())
                    .build();
        }
        // У нас всегда должен быть только один открытый статус
        RefTaskStatus previousTaskStatus = refTaskStatusRepository.findByTaskAndEndedAtIsNull(comment.getTask()).orElse(null);
        if (previousTaskStatus != null) {
            previousTaskStatus.setEndedAt(LocalDateTime.now());
            refTaskStatusRepository.save(previousTaskStatus);
        }
        refTaskStatusRepository.save(taskStatus); // TODO ошибки
        repository.save(comment);

        return true;
    }

    /**
     * Не одобрение действие с задачей
     * @param commentId
     * @return boolean
     */
    private boolean disproveAction(Long commentId) {
        Comment comment = repository.findById(commentId).orElseThrow(); // TODO ошибка ненахождения комментария
        comment.setResultAt(LocalDateTime.now());
        comment.setAction(ActionEnum.disprove);

        // TODO ошибки
        repository.save(comment);

        return true;
    }
}
