package com.tvb.api.domain.member.repository;

import com.tvb.api.domain.member.entity.Profile;
import com.tvb.api.domain.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByNickname(String nickname);
}
