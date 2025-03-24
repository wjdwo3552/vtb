package com.tvb.api.domain.member.repository;

import com.tvb.api.domain.member.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

//    @Query("SELECT u.userNo FROM User u WHERE u.userId = :userId")
//    Optional<User> findUserByUserId(@Param("userId") String userId);
}
