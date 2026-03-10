package atu.ie.oopsem2lab5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})//If this exception is thrown run this code not default
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){ //grabbing info from ex

        Map<String,String> errors = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            String fieldName = error.getField(); //getting name of the field
            String errorMessage = error.getDefaultMessage(); //Error message
            errors.put(fieldName,errorMessage);
        }
        return ResponseEntity.badRequest().body(errors); // Returning hashmap with error field and message
    }

    @ExceptionHandler(ReservationConflictException.class)//If this exception is thrown run this code not default
    public ResponseEntity<String> handleConflict(ReservationConflictException ex){ //grabbing info from ex

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // Returning hashmap with error field and message
    }
}
