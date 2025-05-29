package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.*;
import com.quantumsoft.insurance.enums.*;
import com.quantumsoft.insurance.exception.CustomizedPolicyNotFoundException;
import com.quantumsoft.insurance.exception.PolicyNotFoundException;
import com.quantumsoft.insurance.repository.*;
import com.quantumsoft.insurance.servicei.CustomizedPolicyServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomizedPolicyServiceImpl implements CustomizedPolicyServiceI {

    @Autowired
    private CustomizedPolicyRepository customizedPolicyRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PremiumRepository premiumRepository;

    @Autowired
    private RiderRepository riderRepository;

    @Autowired
    private AddOnRepository addOnRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public String customizePolicy(Long policyId, Long userId, CustomizedPolicy customizedPolicy) {
        Optional<Policy> policyOptional = policyRepository.findById(policyId);
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent() && policyOptional.isPresent()) {
            Policy policy = policyOptional.get();
            customizedPolicy.setTotalPremium(policy.getPremium());
            Users user = userOptional.get();
            if (customizedPolicy.getPremiumPlan().equals(PremiumPlan.MONTHLY)) {
                double monthlyPremium = customizedPolicy.getTotalPremium() / 12;
                LocalDate paymentDate = null;
                List<Premium> premiumList = new ArrayList<>();
                for (int i = 1; i <= 12; i++) {
                    Premium premium = new Premium();
                    premium.setUser(user);
                    premium.setAmountPaid(monthlyPremium);
                    if (i == 1) {
                        premium.setPaymentDate(LocalDate.now());
                        customizedPolicy.setPolicyStartDate(premium.getPaymentDate());
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        premiumList.add(premium);
                    } else {
                        premium.setPaymentDate(paymentDate.plusDays(30));
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        if (i == 12) {
                            customizedPolicy.setPolicyEndDate(premium.getDueDate());
                        }
                        premiumList.add(premium);
                    }
                    premium.setStatus(Status.PENDING);
                    customizedPolicy.setPremiums(premiumList);
                    customizedPolicy.setPolicyStatus(PolicyStatus.PENDING);
                    premium.setCustomizedPolicy(customizedPolicy);
                    //premiumRepository.save(premium);
                    customizedPolicy.setPolicy(policy);
                    customizedPolicy.setUser(user);
                    customizedPolicyRepository.save(customizedPolicy);

                    Map<String, String> notification = new HashMap<>();
                    notification.put("message", "Hi " + user.getFullName() + ", your " + PremiumPlan.MONTHLY + " premiums are generated.");
                    messagingTemplate.convertAndSend("/topic/notifications", notification);
                }
            }
            if (customizedPolicy.getPremiumPlan().equals(PremiumPlan.QUARTERLY)) {
                double quarterlyPremium = customizedPolicy.getTotalPremium() / 4;
                LocalDate paymentDate = null;
                List<Premium> premiumList = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    Premium premium = new Premium();
                    premium.setUser(user);
                    premium.setAmountPaid(quarterlyPremium);
                    if (i == 1) {
                        premium.setPaymentDate(LocalDate.now());
                        customizedPolicy.setPolicyStartDate(premium.getPaymentDate());
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        premiumList.add(premium);
                    } else {
                        premium.setPaymentDate(paymentDate.plusDays(30));
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        if (i == 4) {
                            customizedPolicy.setPolicyEndDate(premium.getDueDate());
                        }
                        premiumList.add(premium);
                    }
                    premium.setStatus(Status.PENDING);
                    customizedPolicy.setPremiums(premiumList);
                    customizedPolicy.setPolicyStatus(PolicyStatus.PENDING);
                    premium.setCustomizedPolicy(customizedPolicy);
                    //premiumRepository.save(premium);
                    customizedPolicy.setPolicy(policy);
                    customizedPolicy.setUser(user);
                    customizedPolicyRepository.save(customizedPolicy);

                    Map<String, String> notification = new HashMap<>();
                    notification.put("message", "Hi " + user.getFullName() + ", your " + PremiumPlan.QUARTERLY + " premiums are generated.");
                    messagingTemplate.convertAndSend("/topic/notifications", notification);
                }
            }
            if (customizedPolicy.getPremiumPlan().equals(PremiumPlan.HALF_YEARLY)) {
                double halfYearlyPremium = customizedPolicy.getTotalPremium() / 2;
                LocalDate paymentDate = null;
                List<Premium> premiumList = new ArrayList<>();
                for (int i = 1; i <= 2; i++) {
                    Premium premium = new Premium();
                    premium.setUser(user);
                    premium.setAmountPaid(halfYearlyPremium);
                    if (i == 1) {
                        premium.setPaymentDate(LocalDate.now());
                        customizedPolicy.setPolicyStartDate(premium.getPaymentDate());
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        premiumList.add(premium);
                    } else {
                        premium.setPaymentDate(paymentDate.plusDays(180));
                        paymentDate = premium.getPaymentDate();
                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
                        customizedPolicy.setPolicyEndDate(premium.getDueDate().plusDays(180));
                        premiumList.add(premium);
                    }
                    premium.setStatus(Status.PENDING);
                    customizedPolicy.setPremiums(premiumList);
                    customizedPolicy.setPolicyStatus(PolicyStatus.PENDING);
                    premium.setCustomizedPolicy(customizedPolicy);
                    //premiumRepository.save(premium);
                    customizedPolicy.setPolicy(policy);
                    customizedPolicy.setUser(user);
                    customizedPolicyRepository.save(customizedPolicy);

                    Map<String, String> notification = new HashMap<>();
                    notification.put("message", "Hi " + user.getFullName() + ", your " + PremiumPlan.HALF_YEARLY + " premiums are generated.");
                    messagingTemplate.convertAndSend("/topic/notifications", notification);
                }
            }
            if (customizedPolicy.getPremiumPlan().equals(PremiumPlan.ANNUALLY)) {

                Premium premium = new Premium();
                premium.setUser(user);
                premium.setAmountPaid(customizedPolicy.getTotalPremium());
                premium.setPaymentDate(LocalDate.now());
                premium.setDueDate(premium.getPaymentDate().plusDays(5));
                List<Premium> premiumList = new ArrayList<>();
                premiumList.add(premium);
                customizedPolicy.setPolicyStartDate(premium.getPaymentDate());
                customizedPolicy.setPolicyEndDate(premium.getDueDate().plusDays(360));
                premium.setStatus(Status.PENDING);
                customizedPolicy.setPremiums(premiumList);
                customizedPolicy.setPolicyStatus(PolicyStatus.PENDING);
                premium.setCustomizedPolicy(customizedPolicy);
                //premiumRepository.save(premium);
                customizedPolicy.setPolicy(policy);
                customizedPolicy.setUser(user);
                customizedPolicyRepository.save(customizedPolicy);

                Map<String, String> notification = new HashMap<>();
                notification.put("message", "Hi " + user.getFullName() + ", your " + PremiumPlan.ANNUALLY + " premiums are generated.");
                messagingTemplate.convertAndSend("/topic/notifications", notification);
            }
            return "Policy customized successfully...!";
        } else
            throw new PolicyNotFoundException("Policy record or User not found in database");
    }

    @Override
    public Rider addRiderToPolicy(Long customizedPolicyId, Long riderId) {
        Optional<CustomizedPolicy> customizedPolicyOptional = customizedPolicyRepository.findById(customizedPolicyId);
        Optional<Rider> riderOptional = riderRepository.findById(riderId);
        if (customizedPolicyOptional.isPresent() && riderOptional.isPresent()) {
            CustomizedPolicy customizedPolicy = customizedPolicyOptional.get();
            Rider rider = riderOptional.get();
            List<Rider> riders = customizedPolicy.getRiders();
            riders.add(rider);
            customizedPolicy.setRiders(riders);
            Double newTotalPremium = rider.getPremiumPerYear() + customizedPolicy.getTotalPremium();
            customizedPolicy.setTotalPremium(newTotalPremium);
            PremiumPlan premiumPlan = customizedPolicy.getPremiumPlan();
            if (premiumPlan.equals(PremiumPlan.MONTHLY)) {
                Double monthlyPremium = customizedPolicy.getTotalPremium() / 12;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(monthlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.QUARTERLY)) {
                Double quarterlyPremium = customizedPolicy.getTotalPremium() / 4;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(quarterlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.HALF_YEARLY)) {
                Double halfYearlyPremium = customizedPolicy.getTotalPremium() / 2;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(halfYearlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.ANNUALLY)) {
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(newTotalPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            customizedPolicyRepository.save(customizedPolicy);
            return rider;
        } else
            throw new CustomizedPolicyNotFoundException("Customized Policy record or Rider record not found in database");
    }

    @Override
    public AddOn addAdOnToPolicy(Long customizedPolicyId, Long addOnId) {
        Optional<CustomizedPolicy> customizedPolicyOptional = customizedPolicyRepository.findById(customizedPolicyId);
        Optional<AddOn> optionalAddOn = addOnRepository.findById(addOnId);
        if (customizedPolicyOptional.isPresent() && optionalAddOn.isPresent()) {
            CustomizedPolicy customizedPolicy = customizedPolicyOptional.get();
            AddOn addOn = optionalAddOn.get();
            List<AddOn> addOns = customizedPolicy.getAddOns();
            addOns.add(addOn);
            customizedPolicy.setAddOns(addOns);
            Double newTotalPremium = addOn.getPremiumPerYear() + customizedPolicy.getTotalPremium();
            customizedPolicy.setTotalPremium(newTotalPremium);
            PremiumPlan premiumPlan = customizedPolicy.getPremiumPlan();
            if (premiumPlan.equals(PremiumPlan.MONTHLY)) {
                Double monthlyPremium = customizedPolicy.getTotalPremium() / 12;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(monthlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.QUARTERLY)) {
                Double quarterlyPremium = customizedPolicy.getTotalPremium() / 4;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(quarterlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.HALF_YEARLY)) {
                Double halfYearlyPremium = customizedPolicy.getTotalPremium() / 2;
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(halfYearlyPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            if (premiumPlan.equals(PremiumPlan.ANNUALLY)) {
                List<Premium> premiums = customizedPolicy.getPremiums();
                premiums.forEach(p -> {
                    p.setAmountPaid(newTotalPremium);
                    p.setUpdatedAt(LocalDateTime.now());
                });
                customizedPolicy.setPremiums(premiums);
            }
            customizedPolicyRepository.save(customizedPolicy);
            return addOn;
        } else
            throw new CustomizedPolicyNotFoundException("Customized Policy record or AddOn record not found in database");
    }
}
