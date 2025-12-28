package com.backouts.innerlink.auth.service;

import com.backouts.innerlink.auth.dto.LoginRequest;
import com.backouts.innerlink.auth.dto.LoginResult;
import com.backouts.innerlink.auth.service.token.AuthTokenManager;
import com.backouts.innerlink.global.error.CustomException;
import com.backouts.innerlink.global.error.ErrorCode;
import com.backouts.innerlink.member.domain.Member;
import com.backouts.innerlink.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthTokenManager authTokenManager;

    protected LoginService(
            MemberRepository memberRepository,
            PasswordEncoder passwordEncoder,
            AuthTokenManager authTokenManager
            ) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authTokenManager = authTokenManager;
    }

    public LoginResult login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.AUTH_FAILED));

        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new CustomException(ErrorCode.AUTH_FAILED);
        }

        return authTokenManager.createToken(member);
    }
}
