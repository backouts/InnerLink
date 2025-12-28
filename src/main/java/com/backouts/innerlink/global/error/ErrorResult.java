package com.backouts.innerlink.global.error;

import java.time.Instant;

public record ErrorResult(
        String code,
        String message,
        Instant timestamp
) {
    public static ErrorResult of(ErrorCode errorCode, String message) {
        return new ErrorResult(errorCode.code(), message, Instant.now());
    }
}
