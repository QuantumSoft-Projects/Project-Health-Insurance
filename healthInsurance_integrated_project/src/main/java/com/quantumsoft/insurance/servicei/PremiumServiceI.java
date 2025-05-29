package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Premium;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.util.List;

public interface PremiumServiceI {

   public String calculatePremium(Double totalPremium, String premiumPlan, Integer duration);

   //public String premiumDetails(Long policyId, Long userId);

   public String payPremium(Long premiumId);

   public String generateReceipt(Long premiumId) throws FileNotFoundException;

   public Resource downloadReceipt(Long premiumId) throws FileNotFoundException;

   public Premium getPremium(Long premiumId);

   public List<Premium> getPremiumsByUserId(Long userId);

   public List<Premium> getPremiumsByPolicyId(Long customizedPolicyId);

   public String renewPolicy(Long customizedPolicyId);

   public String changePolicyStatus(Long customizedPolicyId);

   public List<Premium> getPremiumByDate(String date);
}
