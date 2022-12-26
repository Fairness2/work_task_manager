package ru.gb.worktaskmanager.managercore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.worktaskmanager.managercore.entites.CommentType;

/**
 * Репозиторий типов комментариев
 */
@Repository
public interface CommentTypeRepository extends JpaRepository<CommentType, String> {

}
