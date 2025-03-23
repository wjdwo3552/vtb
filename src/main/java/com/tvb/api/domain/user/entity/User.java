package com.tvb.api.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ucode")
    private Long ucode;  // 회원 고유 코드 (PK, AUTO_INCREMENT)

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;  // 이메일

    @Column(name = "password", nullable = false, length = 255)
    private String password;  // 비밀번호

    @Column(name = "name", length = 20)
    private String name;  // 회원의 전체 이름

    @Column(name = "phone", length = 20)
    private String phone;  // 전화번호

    @Column(name = "grade", length = 20)
    private String grade;  // 등급

    @Column(name = "provider", length = 30)
    private String provider;  // 로그인 제공자

    @Column(name = "created_at")
    private LocalDateTime createdAt;  // 가입 시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // 최근 업데이트 시간

    @Column(name = "profile_image", length = 255)
    private String profileImage;  // 프로필 사진
}
