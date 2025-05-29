package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByVerificationToken(String token);
    Optional<Users> findByResetToken(String token);
}
