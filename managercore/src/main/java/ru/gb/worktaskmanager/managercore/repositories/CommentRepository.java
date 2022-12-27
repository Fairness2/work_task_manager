package ru.gb.worktaskmanager.managercore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.worktaskmanager.managercore.entites.Comment;
import ru.gb.worktaskmanager.managercore.entites.CommentType;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
    public boolean existsByIdAndActionIsNull(Long id);
    public boolean existsByIdAndActionIsNullAndTypeIn(Long id, List<CommentType> types);
}
