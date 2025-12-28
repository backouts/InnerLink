package com.backouts.innerlink.auth.dto;

public record LoginRequest (
    String email,
    String password
) {}
