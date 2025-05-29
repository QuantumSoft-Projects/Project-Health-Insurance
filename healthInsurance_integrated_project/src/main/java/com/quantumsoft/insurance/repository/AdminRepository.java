package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Admin;
import com.quantumsoft.insurance.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Optional<Admin> findByEmail(String username);

    public Optional<Admin> findByOtp(String otp);
}
