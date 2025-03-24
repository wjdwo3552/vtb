package com.tvb.api.domain.user.entity.member;

import com.tvb.api.domain.user.entity.auth.Password;
import com.tvb.api.domain.user.entity.member.logintype.LoginTypeConvertor;
import com.tvb.api.domain.user.entity.member.logintype.LoginType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", schema = "member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", nullable = false, unique = true, updatable = false)
    private Long userNo;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Convert(converter = LoginTypeConvertor.class)
    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Password password;
}
