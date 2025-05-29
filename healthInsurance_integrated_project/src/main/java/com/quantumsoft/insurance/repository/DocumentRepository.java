package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.ClaimDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<ClaimDocument, Long> {
   // List<ClaimDocument> findByClaimId(Long claimId);
}
