package webb.server.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import webb.server.advice.exception.EntryAlreadyExistsException;
import webb.server.advice.exception.ForbiddenActionException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    /**
     * Handles NoSuchElementExceptions by returning a 404 response.
     * @param e The exception to handle.
     * @return A 404 response with the exception's message.
     */
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles different types of bad arg exceptions by returning a 400 response.
     * @param e The exception to handle.
     * @return A 400 response with the exception's message.
     */
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception e) {
        if(e instanceof MethodArgumentNotValidException) {
            String errorMessage = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EntryAlreadyExistsExceptions by returning a 409 response.
     * @param e The exception to handle.
     * @return A 409 response with the exception's message.
     */
    @ExceptionHandler({EntryAlreadyExistsException.class})
    public ResponseEntity<String> handleEntryAlreadyExistsException(Exception e) {
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Handles exceptions that are forbidden by returning a 403 response.
     * @param e The exception to handle.
     * @return A 403 response with the exception's message.
     */
    @ExceptionHandler({ForbiddenActionException.class})
    public ResponseEntity<String> handleForbiddenActionException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
