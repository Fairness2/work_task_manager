package validation.classes;


import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.worktaskmanager.managercore.repositories.CommentRepository;
import validation.intefaces.OpenCommentExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валиадатор для проверки, что комментарий открыт и существует.
 */
public class OpenCommentExistValidator implements ConstraintValidator<OpenCommentExist, Long> {

    CommentRepository repository;

    @Autowired
    public OpenCommentExistValidator(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(OpenCommentExist taskExist){}

    @Override
    public boolean isValid(Long taskId, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsByIdAndActionIsNull(taskId);
    }
}
