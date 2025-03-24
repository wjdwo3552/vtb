package com.tvb.api.domain.member.repository;

import com.tvb.api.domain.member.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
