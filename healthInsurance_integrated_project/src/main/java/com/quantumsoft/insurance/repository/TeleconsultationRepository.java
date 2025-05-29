package com.quantumsoft.insurance.repository;



import com.quantumsoft.insurance.entity.Teleconsultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeleconsultationRepository extends JpaRepository<Teleconsultation, Long> {
    Teleconsultation findByAppointment_AppointmentId(Long appointmentId);
}
