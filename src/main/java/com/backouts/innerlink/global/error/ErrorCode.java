package com.backouts.innerlink.global.error;

public enum ErrorCode {
    // AUTH
    AUTH_FAILED(401, "AUTH_001", "이메일 또는 비밀번호가 올바르지 않습니다."),
    UNAUTHORIZED(401, "AUTH_002", "인증이 필요합니다."),
    FORBIDDEN(403, "AUTH_003", "권한이 없습니다."),

    // MEMBER
    DUPLICATE_EMAIL(409, "MEMBER_001", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(409, "MEMBER_002", "이미 사용 중인 닉네임입니다."),
    PASSWORD_MISMATCH(400, "MEMBER_003", "비밀번호가 일치하지 않습니다."),
    PRIVACY_NOT_AGREED(400, "MEMBER_004", "개인정보 처리 동의가 필요합니다."),
    MEMBER_NOT_FOUND(404, "MEMBER_005", "회원을 찾을 수 없습니다."),

    // POST - 나중에 추가
    POST_NOT_FOUND(404, "POST_001", "게시글을 찾을 수 없습니다."),

    // CHAT - 나중에 추가
    CHAT_ROOM_NOT_FOUND(404, "CHAT_001", "채팅방을 찾을 수 없습니다."),

    // COMMON
    BAD_REQUEST(400, "COMMON_001", "잘못된 요청입니다."),
    INVALID_JSON(400, "COMMON_002", "요청 JSON 형식이 올바르지 않습니다."),
    INTERNAL_ERROR(500, "COMMON_999", "서버 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int status() { return status; }
    public String code() { return code; }
    public String message() { return message; }
}
