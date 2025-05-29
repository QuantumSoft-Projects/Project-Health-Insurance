package com.quantumsoft.insurance.servicei;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendEmailWithAttachment(String to, String subject, String body, String fileName, byte[] attachmentData);
}
