package validation.intefaces;


import validation.classes.DateGreaterOrEqualsThenNowValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

/**
 * Аннотация к валидатору сравнения дат в стринговом представлении
 */
@Constraint(validatedBy = DateGreaterOrEqualsThenNowValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateGreaterOrEqualsThanNow {
    String message() default "Дата меньше, чем текущая";

    String value() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
