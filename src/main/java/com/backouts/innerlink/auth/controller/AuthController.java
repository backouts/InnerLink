package com.backouts.innerlink.auth.controller;

import com.backouts.innerlink.auth.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {

    }
}
