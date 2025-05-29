package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Appointment;
import com.quantumsoft.insurance.entity.Teleconsultation;
import com.quantumsoft.insurance.exception.ResourceNotFoundException;
import com.quantumsoft.insurance.repository.TeleconsultationRepository;
import com.quantumsoft.insurance.servicei.EmailService;
import com.quantumsoft.insurance.servicei.TeleconsultationService;
import com.quantumsoft.insurance.util.PrescriptionPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

@Service
public class TeleconsultationServiceImpl implements TeleconsultationService {

    @Autowired
    private TeleconsultationRepository teleconsultationRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Teleconsultation getByAppointmentId(Long appointmentId) {
        Teleconsultation tele = teleconsultationRepository.findByAppointment_AppointmentId(appointmentId);
        if (tele == null) {
            throw new ResourceNotFoundException("No teleconsultation found for appointment ID: " + appointmentId);
        }
        return tele;
    }

    @Override
    public Teleconsultation getTeleconsultationById(Long id) {
        return teleconsultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teleconsultation not found with id: " + id));
    }

    @Override
    public Teleconsultation updateTeleconsultation(Long id, Teleconsultation updatedData) {
        Teleconsultation existing = teleconsultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teleconsultation not found with ID: " + id));

        existing.setStartTime(updatedData.getStartTime());
        existing.setEndTime(LocalDateTime.now());
        existing.setNotes(updatedData.getNotes());
        existing.setDiagnosis(updatedData.getDiagnosis());
        existing.setPrescription(updatedData.getPrescription());

        Teleconsultation saved = teleconsultationRepository.save(existing);


        ByteArrayInputStream pdfStream = PrescriptionPdfGenerator.generatePrescriptionPdf(saved);
        byte[] pdfBytes;
        pdfBytes = pdfStream.readAllBytes();


        Appointment appointment = saved.getAppointment();
        String emailBody = String.format(
                "Hi %s,\n\nPlease find attached your prescription from Dr. %s.\n\nStay healthy!\n\nQuantumSoft",
                appointment.getUser().getFullName(),
                appointment.getDoctor().getName()
        );


        emailService.sendEmailWithAttachment(
                appointment.getUser().getEmail(),
                "Your Prescription from " + appointment.getDoctor().getName(),
                emailBody,
                "prescription.pdf",
                pdfBytes
        );

        return saved;
    }

}
