package com.backouts.innerlink.member.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    private Boolean privacyAgreed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PresenceStatus presenceStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Roles roles;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateAt;

    protected Member() {}

    public Long getId() {
        return id;
    }

    public static Member signup(
            String email,
            String name,
            String nickname,
            String encodedPassword
    ) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.nickname = nickname;
        member.password = encodedPassword;
        member.roles = Roles.USER;
        member.accountStatus = AccountStatus.ACTIVE;
        member.presenceStatus = PresenceStatus.OFFLINE;
        member.privacyAgreed = true;
        return member;
    }
}
