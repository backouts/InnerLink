package com.backouts.innerlink.member.controller;

import com.backouts.innerlink.member.dto.SignupRequest;
import com.backouts.innerlink.member.service.MemberSignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberSignupService memberSignupService;

    public MemberController(MemberSignupService memberService) {
        this.memberSignupService = memberService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupRequest request) {
        memberSignupService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
