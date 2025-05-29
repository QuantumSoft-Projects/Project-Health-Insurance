package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Claim;
import com.quantumsoft.insurance.entity.ClaimDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByUserId(Long userId);

    //List<ClaimDocument> findAllByClaimId(Long claimId);
}
