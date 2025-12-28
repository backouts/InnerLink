package com.backouts.innerlink.member.service;

import com.backouts.innerlink.global.error.CustomException;
import com.backouts.innerlink.global.error.ErrorCode;
import com.backouts.innerlink.member.domain.Member;
import com.backouts.innerlink.member.dto.SignupRequest;
import com.backouts.innerlink.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberSignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    protected MemberSignupService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(SignupRequest request) {
        if (memberRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNickname(request.nickname())) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        if (!request.password().equals(request.passwordCheck())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        if (!request.privacyAgreed()) {
            throw new CustomException(ErrorCode.PRIVACY_NOT_AGREED);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.signup(
                request.email(),
                request.name(),
                request.nickname(),
                encodedPassword
        );

        memberRepository.save(member);
    }
}
