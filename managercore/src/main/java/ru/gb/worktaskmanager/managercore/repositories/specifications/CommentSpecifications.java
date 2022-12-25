package ru.gb.worktaskmanager.managercore.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.worktaskmanager.managercore.dtos.CommentRequestDto;
import ru.gb.worktaskmanager.managercore.entites.Comment;
import ru.gb.worktaskmanager.managercore.entites.Task;

import javax.persistence.criteria.Join;

public class CommentSpecifications {
    public static Specification<Comment> build(CommentRequestDto requestDto) {
        Specification<Comment> specification = Specification.where(null);
        if (requestDto.getTaskId() != null) {
            specification = specification.and(CommentSpecifications.taskEqual(requestDto.getTaskId()));
        }
        //TODO остальные параметры фильтрации

        return specification;
    }

    private static Specification<Comment> taskEqual(Long taskId) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Comment, Task> taskJoin = root.join("task");
            return criteriaBuilder
                    .equal(taskJoin.get("id"), taskId);
        });
    }
}
