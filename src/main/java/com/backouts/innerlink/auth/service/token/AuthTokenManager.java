package com.backouts.innerlink.auth.service.token;

import com.backouts.innerlink.auth.dto.LoginResult;
import com.backouts.innerlink.member.domain.Member;

public interface AuthTokenManager {
    LoginResult createToken(Member member);
}
