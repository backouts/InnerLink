package com.backouts.innerlink.auth.service.token;

import com.backouts.innerlink.auth.dto.LoginResult;
import com.backouts.innerlink.member.domain.Member;
import com.backouts.innerlink.member.domain.Roles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtTokenManager implements AuthTokenManager {

    private final Key key;
    private final long expSecond;

    protected JwtTokenManager(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.access-exp-seconds}") long expSecond
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expSecond = expSecond;
    }

    @Override
    public LoginResult createToken(Member member) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(expSecond);

        String token = Jwts.builder()
                .setSubject(member.getId().toString())
                .claim("role", member.getRoles())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new LoginResult(token);


    }
}
