package org.trading.productms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.trading.productms.exception.exceptions.InsufficientCount;
import org.trading.productms.exception.exceptions.NotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler({NotFoundException.class, InsufficientCount.class})
    ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.info(ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setDate(LocalDateTime.now());
        errorResponse.setStatus(getHttpStatus(ex));
        errorResponse.setErrorCode(errorResponse.getStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    private HttpStatus getHttpStatus(Exception ex) {
        if (ex instanceof NotFoundException) {
            return HttpStatus.NOT_FOUND;
        } else if (ex instanceof InsufficientCount) {
            return HttpStatus.BAD_REQUEST;
        } else {
            // Handle any other exceptions
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
//    @ExceptionHandler(NotFoundException.class)
//    ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
//        log.info(ex.getMessage(), ex);
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setDate(LocalDateTime.now());
//        errorMessage.setStatus(ex.getStatus());
//        errorMessage.setErrorCode(ex.getStatus().value());
//        errorMessage.setErrorMessage(ex.getMessage());
//        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
//    }

//    @ExceptionHandler(InsufficientCount.class)
//    ResponseEntity<ErrorMessage> handleInsufficientException(InsufficientCount ex) {
//        log.info(ex.getMessage(), ex);
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setDate(LocalDateTime.now());
//        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
//        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
//        errorMessage.setErrorMessage(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//    }
}
