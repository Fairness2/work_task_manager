package validation.intefaces;


import validation.classes.DateGreaterOrEqualsThenValidator;
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
@Constraint(validatedBy = DateGreaterOrEqualsThenValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateGreaterOrEqualsThan {
    String message() default "Первая дата меньше, чем вторая";
    String field();
    String secondField();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        DateGreaterOrEqualsThan[] value();
    }
}
