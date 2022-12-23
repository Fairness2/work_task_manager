package ru.gb.worktaskmanager.managercore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.worktaskmanager.managercore.entites.CommentType;
import ru.gb.worktaskmanager.managercore.repositories.CommentTypeRepository;

import java.util.List;


/**
 * Сервис типов комментариев
 */
@Service
public class CommentTypeService {
    private final CommentTypeRepository repository;

    @Autowired
    public CommentTypeService(CommentTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Получим типы комментариев
     * @return List<CommentType>
     */
    public List<CommentType> getCommentTypes() {
        return repository.findAll();
    }
}
