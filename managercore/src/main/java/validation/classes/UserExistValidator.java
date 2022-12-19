package validation.classes;


import validation.intefaces.UserExist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валиадатор для проверки, что пользователь существует.
 */
public class UserExistValidator implements ConstraintValidator<UserExist, Integer> {
    @Override
    public void initialize(UserExist userExist){}

    @Override
    public boolean isValid(Integer userId, ConstraintValidatorContext constraintValidatorContext) {
        //TODO првоерка в сервисе авторизации
        return true;
    }
}
