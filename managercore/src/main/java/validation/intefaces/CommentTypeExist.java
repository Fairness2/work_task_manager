package validation.intefaces;

import validation.classes.CommentTypeExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация валиадатора для проверки того, что тип комментария существует.
 */
@Documented
@Constraint(validatedBy = CommentTypeExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommentTypeExist {
    String message() default "Тип не существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
