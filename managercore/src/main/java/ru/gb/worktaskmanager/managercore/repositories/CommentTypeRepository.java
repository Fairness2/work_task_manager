package ru.gb.worktaskmanager.managercore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.worktaskmanager.managercore.entites.CommentType;

import java.util.List;

/**
 * Репозиторий типов комментариев
 */
@Repository
public interface CommentTypeRepository extends JpaRepository<CommentType, String> {
    public List<CommentType> getAllByCodeIn(String[] codes);
}
