package com.quantumsoft.insurance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Teleconsultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teleconsultationId;

    private String consultationLink;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String notes;

    private String diagnosis;

    private String prescription;

    @OneToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;

    public Teleconsultation() {}

    public Teleconsultation(Long teleconsultationId, String consultationLink, LocalDateTime startTime, LocalDateTime endTime, String notes, String diagnosis, String prescription, Appointment appointment) {
        this.teleconsultationId = teleconsultationId;
        this.consultationLink = consultationLink;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notes = notes;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.appointment = appointment;
    }

    public Long getTeleconsultationId() {
        return teleconsultationId;
    }

    public void setTeleconsultationId(Long teleconsultationId) {
        this.teleconsultationId = teleconsultationId;
    }

    public String getConsultationLink() {
        return consultationLink;
    }

    public void setConsultationLink(String consultationLink) {
        this.consultationLink = consultationLink;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "Teleconsultation{" +
                "teleconsultationId=" + teleconsultationId +
                ", consultationLink='" + consultationLink + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", notes='" + notes + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescription='" + prescription + '\'' +
                ", appointment=" + appointment +
                '}';
    }
}
