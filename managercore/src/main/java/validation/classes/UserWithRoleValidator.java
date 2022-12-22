package validation.classes;

import validation.intefaces.UserWithRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валиадатор для проверки соответствия даты формату
 */
public class UserWithRoleValidator implements ConstraintValidator<UserWithRole, Long> {
    private String role;
    @Override
    public void initialize(UserWithRole userWithRole){
        this.role = userWithRole.role();
    }

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext) {
        //TODO првоерка в сервисе авторизации
        return true;
    }
}
