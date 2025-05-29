package com.quantumsoft.insurance.entity;

import com.quantumsoft.insurance.enums.PaymentMethod;
import com.quantumsoft.insurance.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Premium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long premiumId;
    @ManyToOne
    private Users user;
    @ManyToOne
    private CustomizedPolicy customizedPolicy;
    private LocalDate paymentDate;
    private LocalDate dueDate;
    private Double amountPaid;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String receiptPath; // to store receipt path
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Premium(){}

    public Premium(Long premiumId, Users user, CustomizedPolicy customizedPolicy, LocalDate paymentDate, LocalDate dueDate, Double amountPaid, PaymentMethod paymentMethod, Status status, String receiptPath, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.premiumId = premiumId;
        this.user = user;
        this.customizedPolicy = customizedPolicy;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.receiptPath = receiptPath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getPremiumId() {
        return premiumId;
    }

    public void setPremiumId(Long premiumId) {
        this.premiumId = premiumId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public CustomizedPolicy getCustomizedPolicy() {
        return customizedPolicy;
    }

    public void setCustomizedPolicy(CustomizedPolicy customizedPolicy) {
        this.customizedPolicy = customizedPolicy;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReceiptPath() {
        return receiptPath;
    }

    public void setReceiptPath(String receiptPath) {
        this.receiptPath = receiptPath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

