package com.backouts.innerlink.member.dto;

public record SignupRequest (
        String name,
        String nickname,
        String email,
        String password,
        String passwordCheck,
        Boolean privacyAgreed
) {}
