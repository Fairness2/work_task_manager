package ru.gb.worktaskmanager.managercore.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.worktaskmanager.managercore.dtos.TaskRequestDto;
import ru.gb.worktaskmanager.managercore.entites.Task;

import java.util.List;

public class TaskSpecifications {
    public static Specification<Task> build(TaskRequestDto requestDto) {
        Specification<Task> specification = Specification.where(null);
        if (requestDto.getUserId() != null) {
            specification = specification.and(TaskSpecifications.userEqual(requestDto.getUserId()));
        }
        //TODO остальные параметры фильтрации

        return specification;
    }

    private static Specification<Task> userEqual(Integer userId) {
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("employer_id"), userId)
        );
    }
}
