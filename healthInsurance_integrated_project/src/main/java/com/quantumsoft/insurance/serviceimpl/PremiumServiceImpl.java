package com.quantumsoft.insurance.serviceimpl;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.quantumsoft.insurance.entity.CustomizedPolicy;
import com.quantumsoft.insurance.entity.Premium;
import com.quantumsoft.insurance.enums.PaymentMethod;
import com.quantumsoft.insurance.enums.PolicyStatus;
import com.quantumsoft.insurance.enums.Status;
import com.quantumsoft.insurance.exception.CustomizedPolicyNotFoundException;
import com.quantumsoft.insurance.exception.PremiumRecordNotFoundException;
import com.quantumsoft.insurance.repository.CustomizedPolicyRepository;
import com.quantumsoft.insurance.repository.PremiumRepository;
import com.quantumsoft.insurance.repository.UserRepository;
import com.quantumsoft.insurance.servicei.PremiumServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PremiumServiceImpl implements PremiumServiceI {

    @Autowired
    private PremiumRepository premiumRepository;

    @Autowired
    private CustomizedPolicyRepository customizedPolicyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String calculatePremium(Double totalPremium, String premiumPlan, Integer duration) {
        if(premiumPlan.equals("MONTHLY")) {
            Double monthlyPremium = totalPremium/duration;
            return monthlyPremium.toString();
        }

        if(premiumPlan.equals("QUARTERLY")){
            Double quarterlyPremium = totalPremium/duration;
            return quarterlyPremium.toString();
        }

            if(premiumPlan.equals("HALF_YEARLY")){
            Double halfYearlyPremium = totalPremium/duration;
            return halfYearlyPremium.toString();
        }

        if(premiumPlan.equals("ANNUALLY")){
            return totalPremium.toString();
        }
        return null;
    }

//    @Override
//    public String premiumDetails(Long customizedPolicyId, Long userId) {
//        Optional<CustomizedPolicy> optional = customizedPolicyRepository.findById(customizedPolicyId);
//        if(optional.isPresent()){
//            CustomizedPolicy customizedPolicy = optional.get();
//            Optional<User> userOptional = userRepository.findById(userId);
//            if(userOptional.isPresent()) {
//                User user = userOptional.get();
//                if (customizedPolicy.getPremiumPlan().name().equals("MONTHLY")) {
//                    double monthlyPremium = customizedPolicy.getPolicy().getPremium() / 12;
//                    LocalDate paymentDate = null;
//                    for (int i = 1; i <= 12; i++) {
//                        Premium premium = new Premium();
//                        premium.setUser(user);
//                        premium.setPolicy(policy);
//                        premium.setAmountPaid(monthlyPremium);
//                        if(i==1) {
//                            premium.setPaymentDate(LocalDate.now());
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }else{
//                            premium.setPaymentDate(paymentDate.plusDays(30));
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }
//                        premium.setStatus(Status.PENDING);
//                        premiumRepository.save(premium);
//                    }
//                }
//                if (policy.getPremiumPlan().name().equals("QUARTERLY")) {
//                    double monthlyPremium = policy.getPremium() / 4;
//                    LocalDate paymentDate = null;
//                    for (int i = 1; i <= 4; i++) {
//                        Premium premium = new Premium();
//                        premium.setUser(user);
//                        premium.setPolicy(policy);
//                        premium.setAmountPaid(monthlyPremium);
//                        if(i==1) {
//                            premium.setPaymentDate(LocalDate.now());
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }else{
//                            premium.setPaymentDate(paymentDate.plusDays(90));
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }
//                        premium.setStatus(Status.PENDING);
//                        premiumRepository.save(premium);
//                    }
//                }
//                if (policy.getPremiumPlan().name().equals("HALF_YEARLY")) {
//                    double monthlyPremium = policy.getPremium() / 2;
//                    LocalDate paymentDate = null;
//                    for (int i = 1; i <= 2; i++) {
//                        Premium premium = new Premium();
//                        premium.setUser(user);
//                        premium.setPolicy(policy);
//                        premium.setAmountPaid(monthlyPremium);
//                        if(i==1) {
//                            premium.setPaymentDate(LocalDate.now());
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }else{
//                            premium.setPaymentDate(paymentDate.plusDays(180));
//                            paymentDate = premium.getPaymentDate();
//                            premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        }
//                        premium.setStatus(Status.PENDING);
//                        premiumRepository.save(premium);
//                    }
//                }
//                if (policy.getPremiumPlan().name().equals("ANNUALLY")) {
//                        Premium premium = new Premium();
//                        premium.setUser(user);
//                        premium.setPolicy(policy);
//                        premium.setAmountPaid(policy.getPremium());
//                        premium.setPaymentDate(LocalDate.now());
//                        premium.setDueDate(premium.getPaymentDate().plusDays(5));
//                        premiumRepository.save(premium);
//                }
//            }
//        }
//        return "Premiums created successfully";
//    }

    @Override
    public String payPremium(Long premiumId) {
        Optional<Premium> optional = premiumRepository.findById(premiumId);
        if(optional.isPresent()) {
            Premium premium = optional.get();
            if(premium.getStatus().name().equals("PENDING")){
                // write payment logic
                premium.setPaymentMethod(PaymentMethod.UPI);
                premium.setStatus(Status.PAID);
                premium.getCustomizedPolicy().setPolicyStatus(PolicyStatus.ACTIVE);
                premium.setUpdatedAt(LocalDateTime.now());
                premiumRepository.save(premium);
                return "Premium paid successfully";
            }
            return "Premium is already PAID";
        }
        throw new PremiumRecordNotFoundException("Premium record not present in database");
    }

    @Override
    public String generateReceipt(Long premiumId) throws FileNotFoundException {
        Optional<Premium> optional = premiumRepository.findById(premiumId);
        if(optional.isPresent()) {
            Premium premium = optional.get();
            File dir = new File("receipts/premium_receipt");
            if (!dir.exists()) dir.mkdirs();

            String fileName = "receipt_premium_" + premium.getPremiumId() + ".pdf";
            String filePath = "receipts/premium_receipt" + File.separator + fileName;

            PdfWriter writer = new PdfWriter(new FileOutputStream(filePath));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Health Insurance Premium Receipt")
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));

            Table table = new Table(UnitValue.createPercentArray(new float[]{30, 70}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addCell(new Cell().add(new Paragraph("Receipt ID").setFontSize(10)));
            table.addCell(String.valueOf(premium.getPremiumId()));

            table.addCell(new Cell().add(new Paragraph("User ID").setFontSize(10)));
            table.addCell(String.valueOf(premium.getUser().getUserId()));

            table.addCell(new Cell().add(new Paragraph("Policy ID").setFontSize(10)));
            table.addCell(String.valueOf(premium.getCustomizedPolicy().getCustomizedPolicyId()));

            table.addCell(new Cell().add(new Paragraph("Payment Date").setFontSize(10)));
            table.addCell(premium.getPaymentDate().format(DateTimeFormatter.ISO_DATE));

            table.addCell(new Cell().add(new Paragraph("Amount Paid ").setFontSize(10)));
            table.addCell("₹" + premium.getAmountPaid());

            table.addCell(new Cell().add(new Paragraph("Payment Method").setFontSize(10)));
            table.addCell(String.valueOf(premium.getPaymentMethod()));

            table.addCell(new Cell().add(new Paragraph("Status").setFontSize(10)));
            table.addCell(String.valueOf(premium.getStatus()));

            document.add(table);

            document.add(new Paragraph("Thank you for your payment!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20));

            document.close();
            premium.setReceiptPath(filePath);
            premiumRepository.save(premium);
            return filePath;
        }
        throw new PremiumRecordNotFoundException("Premium record with given premium id is not present in database");
    }

    @Override
    public Resource downloadReceipt(Long premiumId) throws FileNotFoundException {
        Optional<Premium> optional = premiumRepository.findById(premiumId);
        if(optional.isPresent()){
            Premium premium = optional.get();
            File file = new File(premium.getReceiptPath());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return resource;
        }
        throw new PremiumRecordNotFoundException("Premium record with given premium id is not present in database");
    }

    @Override
    public Premium getPremium(Long premiumId) {
        Optional<Premium> optional = premiumRepository.findById(premiumId);
        if(optional.isPresent())
            return optional.get();
        else
            throw new PremiumRecordNotFoundException("Premium record with given premium id is not present in database");
    }

    @Override
    public List<Premium> getPremiumsByUserId(Long userId) {
        return premiumRepository.findAll().stream().filter(p -> p.getUser().getUserId().equals(userId)).toList();
    }

    @Override
    public List<Premium> getPremiumsByPolicyId(Long customizedPolicyId) {
        Optional<CustomizedPolicy> customizedPolicyOptional = customizedPolicyRepository.findById(customizedPolicyId);
        if(customizedPolicyOptional.isPresent()){
            return customizedPolicyOptional.get().getPremiums();
        }else
            throw new CustomizedPolicyNotFoundException("Customized policy record not found in database");
    }

    @Override
    public String renewPolicy(Long customizedPolicyId) {
        Optional<CustomizedPolicy> customizedPolicyOptional = customizedPolicyRepository.findById(customizedPolicyId);
        if(customizedPolicyOptional.isPresent()){
            CustomizedPolicy customizedPolicy = customizedPolicyOptional.get();
            if(LocalDate.now().equals(customizedPolicy.getPolicyEndDate().plusDays(1))){
                return "Your policy term duration is over, please renew the policy";
            }else
                return "Policy term duration is yet to over.";
        }else
            throw new CustomizedPolicyNotFoundException("Customized policy record not found in database");
    }

//    @Override
//    public String renewPolicy(Long customizedPolicyId) {
//        List<Premium> list = premiumRepository.findAll().stream().filter(p -> Objects.equals(p.getPolicy().getId(), id)).toList();// with policy Premium class is not mapped that why we are taking premium
//        LocalDate firstPaymentDate = LocalDate.parse(date);
//        Optional<Premium> optional =premiumRepository.findByPaymentDate(firstPaymentDate);
//        //Optional<Premium> optional = list.stream().filter(p -> p.getPaymentDate().equals(parsedDate)).findAny();
//        if(optional.isPresent()){
//            Premium premium = optional.get();
//            int termDuration = premium.getPolicy().getTermDuration();
//            if(termDuration==1) {
//                LocalDate lastPaymentDate = firstPaymentDate.plusDays(360);
//                if (LocalDate.now().equals(lastPaymentDate.plusDays(1))) {
//                    return "Your policy term duration is over, please renew the policy";
//                }
//            }
//            if(termDuration==2) {
//                LocalDate lastPaymentDate = firstPaymentDate.plusDays(720);
//                if (LocalDate.now().equals(lastPaymentDate.plusDays(1))) {
//                    return "Your policy term duration is over, please renew the policy";
//                }
//            }
//            if(termDuration==3) {
//                LocalDate lastPaymentDate = firstPaymentDate.plusDays(1180);
//                if (LocalDate.now().equals(lastPaymentDate.plusDays(1))) {
//                    return "Your policy term duration is over, please renew the policy";
//                }
//            }
//        }
//        return "null";
//   }

    @Override
    public String changePolicyStatus(Long customizedPolicyId) {
        Optional<CustomizedPolicy> customizedPolicyOptional = customizedPolicyRepository.findById(customizedPolicyId);
        if(customizedPolicyOptional.isPresent()) {
            CustomizedPolicy customizedPolicy = customizedPolicyOptional.get();
            if(customizedPolicy.getPremiums().stream().allMatch(p -> p.getStatus().equals(Status.PENDING))){
                customizedPolicy.setPolicyStatus(PolicyStatus.EXPIRED);
                return "Policy is expired as no premiums have been paid yet.";
            }
            if(customizedPolicy.getPolicyEndDate().plusDays(1).equals(LocalDate.now())){
                customizedPolicy.setPolicyStatus(PolicyStatus.EXPIRED);
                return "Policy is expired as policy term duration over";
            }
            return "Policy is " + customizedPolicy.getPolicyStatus().name();
        }else
            throw new CustomizedPolicyNotFoundException("Customized policy record not found in database");
    }

    @Override
    public List<Premium> getPremiumByDate(String date) {
        return premiumRepository.findAll().stream().filter(p -> p.getPaymentDate().equals(LocalDate.parse(date))).toList();
    }
}
