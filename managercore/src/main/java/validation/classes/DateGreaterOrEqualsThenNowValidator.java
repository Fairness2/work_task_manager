package validation.classes;

import org.springframework.beans.BeanWrapperImpl;
import validation.intefaces.DateGreaterOrEqualsThanNow;
import validation.intefaces.StringDateGreaterOrEqualsThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Валиадатор для проверки что одна дата больше или равно другой.
 */
public class DateGreaterOrEqualsThenNowValidator implements ConstraintValidator<DateGreaterOrEqualsThanNow, LocalDateTime> {

    String value;

    @Override
    public void initialize(DateGreaterOrEqualsThanNow greaterOrEqualsThan){
        this.value = greaterOrEqualsThan.value();
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext constraintValidatorContext) {
        return validateField(date);
    }

    private boolean validateField(LocalDateTime date) {
        LocalDateTime dateFrom;
        if (this.value.isEmpty()) {
            dateFrom = LocalDateTime.now();
        } else {
            dateFrom = LocalDateTime.parse(this.value);
        }

        return !date.isBefore(dateFrom);
    }
}
