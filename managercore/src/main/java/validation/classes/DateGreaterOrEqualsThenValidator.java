package validation.classes;

import org.springframework.beans.BeanWrapperImpl;
import validation.intefaces.DateGreaterOrEqualsThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * Валиадатор для проверки что одна дата больше или равно другой.
 */
public class DateGreaterOrEqualsThenValidator implements ConstraintValidator<DateGreaterOrEqualsThan, Object> {

    private String field;
    private String secondField;

    @Override
    public void initialize(DateGreaterOrEqualsThan greaterOrEqualsThan){
        this.field = greaterOrEqualsThan.field();
        this.secondField = greaterOrEqualsThan.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return validateClass(object);
    }

    private boolean validateClass(Object object) {
        Object value = new BeanWrapperImpl(object).getPropertyValue(this.field);
        Object secondValue = new BeanWrapperImpl(object).getPropertyValue(this.secondField);
        if (value == null || secondValue == null) {
            // Если одно из значений не указано, то считаем, что проверка выполнена
            return true;
        }
        if (!(value instanceof LocalDateTime) || !(secondValue instanceof LocalDateTime) ) {
            //throw new  NumberFormatException("Значения не являются числами");
            return false;
        }

        LocalDateTime dateFrom = (LocalDateTime) secondValue;
        LocalDateTime dateTo = (LocalDateTime) value;


        return !dateTo.isBefore(dateFrom);
    }
}
