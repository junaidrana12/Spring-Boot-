package com.demo.first.app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception Handling Method
    @ExceptionHandler({IllegalArgumentException.class, UserNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            Exception exception
    ) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", exception.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Bad request");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception){
        Map<String , Object> error = new HashMap<>();
        error.put("message ",exception.getMessage());

        error.put("Request Method ", "does not supprot");
        error.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }

}


//    {
//        "timestamp": "2026-02-04T17:32:18.628+00:00",
//            "status": 500,
//            "error": "Internal Server Error",
//            "path": "/user"
//    }



