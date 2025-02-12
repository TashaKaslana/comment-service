package org.phong.commentservice.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception exception) {
        System.out.println("Handling global exception: " + exception.getMessage());
        exception.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 500);
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> commentNotFoundException(CommentNotFoundException exception) {
        System.out.println("Comment is not found in database: " + exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 404);
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(InvalidInteractionTypeException.class)
    public ResponseEntity<ErrorResponse> invalidInteractionTypeException(InvalidInteractionTypeException exception) {
        System.out.println("Invalid interaction type: " + exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), 400);
        return ResponseEntity.status(errorResponse.getStatusCode()).body(errorResponse);
    }

    @Getter
    @ToString
    public static class ErrorResponse {
        private final String message;
        private final int statusCode;
        private final LocalDateTime timestamp;

        public ErrorResponse(String message, int statusCode) {
            this.message = message;
            this.statusCode = statusCode;
            this.timestamp = LocalDateTime.now();
        }
    }
}
