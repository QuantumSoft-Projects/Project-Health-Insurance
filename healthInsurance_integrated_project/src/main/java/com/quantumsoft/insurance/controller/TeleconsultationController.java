package com.quantumsoft.insurance.controller;

import com.quantumsoft.insurance.entity.Teleconsultation;
import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
import com.quantumsoft.insurance.servicei.TeleconsultationService;
import com.quantumsoft.insurance.serviceimpl.JwtService;
import com.quantumsoft.insurance.util.PrescriptionPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/teleconsultation")
@CrossOrigin("*")
public class TeleconsultationController {

    @Autowired
    private TeleconsultationService teleconsultationService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<Teleconsultation> getByAppointmentId(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(teleconsultationService.getByAppointmentId(appointmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teleconsultation> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teleconsultationService.getTeleconsultationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teleconsultation> updateTeleconsultation(
            @PathVariable Long id,
            @RequestBody Teleconsultation updatedData, @RequestHeader("Authorization")String bearerToken
    ) {
        String token = bearerToken.substring(7);
        String role = jwtService.extractRole(token);
        if(role.equals("HOSPITALADMIN"))
            return ResponseEntity.ok(teleconsultationService.updateTeleconsultation(id, updatedData));
        else
            throw new UnAuthorizedAccessException("Unauthorized Access");
    }

    @GetMapping("/download-prescription/{appointmentId}")
    public ResponseEntity<byte[]> downloadPrescriptionPdf(@PathVariable Long appointmentId) throws IOException {
        Teleconsultation tele = teleconsultationService.getByAppointmentId(appointmentId);

        ByteArrayInputStream pdfStream = PrescriptionPdfGenerator.generatePrescriptionPdf(tele);
        byte[] pdfBytes = pdfStream.readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "prescription.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
