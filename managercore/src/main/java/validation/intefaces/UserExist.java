package validation.intefaces;

import validation.classes.UserExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация валиадатора для проверки того, что пользователь существует.
 */
@Documented
@Constraint(validatedBy = UserExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserExist {
    String message() default "Пользователя не существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
