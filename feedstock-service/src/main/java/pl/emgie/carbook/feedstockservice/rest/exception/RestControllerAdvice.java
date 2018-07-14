package pl.emgie.carbook.feedstockservice.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.emgie.commons.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Resource not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
