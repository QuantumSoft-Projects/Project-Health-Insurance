package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Claim;
import com.quantumsoft.insurance.entity.ClaimDocument;
import com.quantumsoft.insurance.entity.Users;
import com.quantumsoft.insurance.enums.ClaimStatus;
import com.quantumsoft.insurance.enums.PremiumPlan;
import com.quantumsoft.insurance.repository.ClaimRepository;
import com.quantumsoft.insurance.repository.UserRepository;
import com.quantumsoft.insurance.servicei.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRepository claimRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserRepository userRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Claim submitClaim(Claim claim) {
//        Claim claim1 = new Claim();
        claim.setUserId(claim.getUserId());
        claim.setPolicyId(claim.getPolicyId());
        claim.setClaimType(claim.getClaimType());
        claim.setIncidentDate(claim.getIncidentDate());
        claim.setAmount(claim.getAmount());
        claim.setDescription(claim.getDescription());
        claim.setStatus(ClaimStatus.SUBMITTED);
        claim.setCreatedAt(LocalDateTime.now());
        claim.setUpdatedAt(LocalDateTime.now());

        return claimRepository.save(claim);
    }

//    @Override
//    public Claim submitClaim(Claim claim) {
//        return claimRepository.save(claim);
//    }



    @Override
    public List<Claim> getClaimsByUser(Long userId) {
        return claimRepository.findByUserId(userId);
    }

    @Override
    public Claim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with id: " + claimId));
    }

//    @Override
//    public void updateStatus(Long id, Cla) {



    @Override
    public void updateStatus(Long claimId, ClaimStatus claimStatus) {
        Claim claim = getClaim(claimId);
        claim.setStatus(claimStatus);
        claim.setUpdatedAt(LocalDateTime.now());
        claimRepository.save(claim);

        Optional<Users> usersOptional = userRepository.findById(claim.getUserId());

        Map<String, String> notification = new HashMap<>();
        notification.put("message", "Hi " + usersOptional.get().getFullName() + ", your claim status updated to " + claim.getStatus().name());
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

    @Override
    public List<ClaimDocument> getDocuments(Long claimId) {
        Optional<Claim> claimOptional = claimRepository.findById(claimId);
        if(claimOptional.isPresent()){
            Claim claim = claimOptional.get();
            return claim.getDocuments();
        }else
            return null;
    }
}
