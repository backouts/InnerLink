package com.backouts.innerlink.member.service;

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
        if (memberRepository.existsByEmail(request.email)) {
            throw new RuntimeException("이메일이 중복됩니다.");
        }

        if (memberRepository.existsByNickname(request.nickname)) {
            throw new RuntimeException("닉네임이 중복됩니다.");
        }


        if (!request.password.equals(request.passwordCheck)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        String encodedPassword = passwordEncoder.encode(request.password);

        Member member = Member.signup(
                request.email,
                request.name,
                request.nickname,
                encodedPassword
        );

        memberRepository.save(member);
    }
}
