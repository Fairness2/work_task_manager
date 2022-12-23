package validation.classes;


import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.worktaskmanager.managercore.repositories.TaskRepository;
import validation.intefaces.TaskExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валиадатор для проверки, что тип задание существует.
 */
public class TaskExistValidator implements ConstraintValidator<TaskExist, Long> {

    TaskRepository repository;

    @Autowired
    public TaskExistValidator(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void initialize(TaskExist taskExist){}

    @Override
    public boolean isValid(Long taskId, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(taskId);
    }
}
