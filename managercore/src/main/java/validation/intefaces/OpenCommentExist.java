package validation.intefaces;

import validation.classes.OpenCommentExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация валиадатора для проверки того, что комментарий открыт и существует.
 */
@Documented
@Constraint(validatedBy = OpenCommentExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenCommentExist {
    String message() default "Комментарий закрыт или не существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
