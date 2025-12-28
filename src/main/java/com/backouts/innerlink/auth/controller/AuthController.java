package com.backouts.innerlink.auth.controller;

import com.backouts.innerlink.auth.dto.LoginRequest;
import com.backouts.innerlink.auth.dto.LoginResult;
import com.backouts.innerlink.auth.service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final LoginService loginService;

    protected AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        LoginResult loginResult = loginService.login(request);

        ResponseCookie cookie = ResponseCookie.from("access_token", loginResult.accessToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
