package com.xplained.main.user.bio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBioRepository extends JpaRepository<UserBio, Long> {
    Optional<UserBio> findByUserId(Long userId);
}
