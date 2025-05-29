package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Premium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PremiumRepository extends JpaRepository<Premium, Long> {

    public Optional<Premium> findByPaymentDate(LocalDate parsedDate);
}
