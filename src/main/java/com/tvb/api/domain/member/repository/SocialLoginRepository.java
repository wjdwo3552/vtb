package com.tvb.api.domain.member.repository;

import com.tvb.api.domain.member.entity.SocialLogin;
import com.tvb.api.domain.member.entity.User;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long> {
    Optional<SocialLogin> findBySocialId(String socialId);

    @Query("SELECT s.user FROM SocialLogin s WHERE s.socialId = :socialId")
    Optional<User> findUserBySocialId(String socialId);
}
