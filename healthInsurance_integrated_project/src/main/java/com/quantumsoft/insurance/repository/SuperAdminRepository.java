package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
   public Optional<SuperAdmin> findByUsername(String username);
}
