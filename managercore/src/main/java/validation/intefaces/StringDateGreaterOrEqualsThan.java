package validation.intefaces;


import validation.classes.StringDateGreaterOrEqualsThenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация к валидатору сравнения дат в стринговом представлении
 */
@Constraint(validatedBy = StringDateGreaterOrEqualsThenValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringDateGreaterOrEqualsThan {
    String message() default "Первая дата меньше, чем вторая";
    String field();
    String secondField();
    String format() default "yyyy-MM-dd";
    String value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        StringDateGreaterOrEqualsThan[] value();
    }
}
