package demo.test.lucas.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsApi {

    @ExceptionHandler( EntityNotFoundException.class )
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity error400( MethodArgumentNotValidException ex ) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body( errors.stream().map( DataErrorsValidation :: new ).toList() );
    }

    private record DataErrorsValidation(String field, String message) {
        public DataErrorsValidation( FieldError error ) {
            this( error.getField(), error.getDefaultMessage() );
        }
    }

}
