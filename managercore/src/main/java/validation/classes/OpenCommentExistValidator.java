package validation.classes;


import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.worktaskmanager.managercore.entites.CommentType;
import ru.gb.worktaskmanager.managercore.entites.CommentTypeEnum;
import ru.gb.worktaskmanager.managercore.repositories.CommentRepository;
import ru.gb.worktaskmanager.managercore.repositories.CommentTypeRepository;
import validation.intefaces.OpenCommentExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Валиадатор для проверки, что комментарий открыт и существует.
 */
public class OpenCommentExistValidator implements ConstraintValidator<OpenCommentExist, Long> {

    CommentRepository repository;
    CommentTypeRepository typeRepository;

    @Autowired
    public OpenCommentExistValidator(CommentRepository repository, CommentTypeRepository typeRepository) {
        this.repository = repository;
        this.typeRepository = typeRepository;
    }

    @Override
    public void initialize(OpenCommentExist taskExist){}

    @Override
    public boolean isValid(Long taskId, ConstraintValidatorContext constraintValidatorContext) {
        String[] typeCodes = new String[]{
                CommentTypeEnum.pause_request.name(),
                CommentTypeEnum.start_request.name(),
                CommentTypeEnum.done_request.name()
        };
        List<CommentType> types = typeRepository.getAllByCodeIn(typeCodes);

        return repository.existsByIdAndActionIsNullAndTypeIn(taskId, types);
        //return repository.existsByIdAndActionIsNull(taskId);
    }
}
