package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser_UserId(Long userId);
    List<Appointment> findByDoctor_DoctorId(Long doctorId);
}
