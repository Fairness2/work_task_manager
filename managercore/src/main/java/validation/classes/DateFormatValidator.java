package validation.classes;


import validation.intefaces.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Валиадатор для проверки наличия такой категории.
 */
public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {
    private SimpleDateFormat dateFormat;
    @Override
    public void initialize(DateFormat dateFormat){
        this.dateFormat =  new SimpleDateFormat(dateFormat.format());
    }

    @Override
    public boolean isValid(String stringDate, ConstraintValidatorContext constraintValidatorContext) {
        Date date;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // Не удалось спарсить данные, значит не соответствует формату
            return false;
        }

        return true;
    }
}
