package validation.classes;

import org.springframework.beans.BeanWrapperImpl;
import validation.intefaces.StringDateGreaterOrEqualsThan;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Валиадатор для проверки что одна дата больше или равно другой.
 */
public class StringDateGreaterOrEqualsThenValidator implements ConstraintValidator<StringDateGreaterOrEqualsThan, Object> {

    private String field;
    private String secondField;
    private String value;
    private String format;

    @Override
    public void initialize(StringDateGreaterOrEqualsThan greaterOrEqualsThan){
        this.field = greaterOrEqualsThan.field();
        this.secondField = greaterOrEqualsThan.secondField();

        this.value = greaterOrEqualsThan.value();
        this.format = greaterOrEqualsThan.format();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object instanceof String) {
            return validateField((String) object);
        } else {
            return validateClass(object);
        }
    }

    private boolean validateClass(Object object) {
        Object value = new BeanWrapperImpl(object).getPropertyValue(this.field);
        Object secondValue = new BeanWrapperImpl(object).getPropertyValue(this.secondField);
        if (value == null || secondValue == null) {
            // Если одно из значений не указано, то считаем, что проверка выполнена
            return true;
        }
        if (!(value instanceof String) || !(secondValue instanceof String) ) {
            //throw new  NumberFormatException("Значения не являются числами");
            return false;
        }

        String sDateFrom = (String) secondValue;
        String sDateTo = (String) value;
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
        Date dateFrom;
        Date dateTo;
        try {
            dateFrom = dateFormat.parse(sDateFrom);
            dateTo = dateFormat.parse(sDateTo);
        } catch (ParseException e) {
            // Не удалось спарсить данные
            return false;
        }

        return dateTo.getTime() >= dateFrom.getTime();
    }

    private boolean validateField(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.format);
        Date dateFrom;
        Date dateTo;
        try {
            if (value == null || value.equals("")) {
                dateFrom = new Date();
            } else {
                dateFrom = dateFormat.parse(value);
            }

            dateTo = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // Не удалось спарсить данные
            return false;
        }

        return dateTo.getTime() >= dateFrom.getTime();
    }
}
