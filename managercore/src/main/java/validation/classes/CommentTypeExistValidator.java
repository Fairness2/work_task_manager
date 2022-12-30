package validation.classes;


import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.worktaskmanager.managercore.repositories.CommentTypeRepository;
import validation.intefaces.CommentTypeExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валиадатор для проверки, что тип комментария существует.
 */
public class CommentTypeExistValidator implements ConstraintValidator<CommentTypeExist, String> {

    CommentTypeRepository repository;

    @Autowired
    public CommentTypeExistValidator(CommentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(CommentTypeExist commentTypeExist){}

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(type);
    }
}
