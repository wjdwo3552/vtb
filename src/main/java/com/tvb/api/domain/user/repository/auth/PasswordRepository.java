package com.tvb.api.domain.user.repository.auth;

import com.tvb.api.domain.user.entity.auth.Password;
import com.tvb.api.domain.user.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("SELECT p.password FROM Password p WHERE p.user = :user")
    Optional<String> findPasswordByUser(@Param("user") User user);

}
