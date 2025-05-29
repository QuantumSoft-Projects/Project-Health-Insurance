package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Claim;
import com.quantumsoft.insurance.entity.ClaimDocument;
import com.quantumsoft.insurance.enums.ClaimStatus;

import java.util.List;
public interface ClaimService {
    Claim submitClaim(Claim claim);

    List<Claim> getClaimsByUser(Long userId);


    Claim getClaim(Long claimId);

    void updateStatus(Long claimId, ClaimStatus claimStatus);

   public List<ClaimDocument> getDocuments(Long claimId);
}
