package com.bkartisan.be.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        // Create an ErrorResponse object with the desired message
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error");

        // Return a ResponseEntity with status 500 (INTERNAL_SERVER_ERROR) and the
        // ErrorResponse body
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}