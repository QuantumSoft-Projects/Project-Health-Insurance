package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Teleconsultation;

public interface TeleconsultationService {
    Teleconsultation getByAppointmentId(Long appointmentId);
    Teleconsultation updateTeleconsultation(Long id, Teleconsultation updatedData);
    Teleconsultation getTeleconsultationById(Long id);
}
