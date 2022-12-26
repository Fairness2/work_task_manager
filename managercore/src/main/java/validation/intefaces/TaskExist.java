package validation.intefaces;

import validation.classes.TaskExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация валиадатора для проверки того, что задание существует.
 */
@Documented
@Constraint(validatedBy = TaskExistValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskExist {
    String message() default "Задание не существует";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
