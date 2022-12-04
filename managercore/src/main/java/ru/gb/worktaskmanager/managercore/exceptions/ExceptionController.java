package ru.gb.worktaskmanager.managercore.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.worktaskmanager.managercore.dtos.ProjectError;
import ru.gb.worktaskmanager.managercore.dtos.ProjectFormError;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * Ошибка возникающая при валидации дто
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = null;
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            } else {
                //fieldName = ((ObjectError) error).getObjectName();
                fieldName = "formException";
            }

            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ProjectFormError projectFormError = new ProjectFormError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), errors);
        return new ResponseEntity<>(projectFormError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Ошибка возникающая при валидации инпута в функцию контроллера
     * @return ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationExceptions(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ProjectError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
