package com.backouts.innerlink.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 에러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResult> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.status())
                .body(ErrorResult.of(errorCode, e.getMessage()));
    }

    // 예상 못한 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleUnhandled(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        return ResponseEntity
                .status(errorCode.status())
                .body(ErrorResult.of(errorCode, errorCode.message()));
    }
}
