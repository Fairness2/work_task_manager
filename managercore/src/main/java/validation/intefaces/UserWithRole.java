package validation.intefaces;

import validation.classes.UserWithRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Аннотация валиадатора для проверки того, что у пользователя есть нужная роль.
 */
@Documented
@Constraint(validatedBy = UserWithRoleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserWithRole {
    String message() default "У пользователя нет необходимой роли";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String role();
}
