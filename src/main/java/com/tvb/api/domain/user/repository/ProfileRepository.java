package com.tvb.api.domain.user.repository;

import com.tvb.api.domain.user.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
