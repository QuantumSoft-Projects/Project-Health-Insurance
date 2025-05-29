package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Long hospitalId, Doctor doctor);
    Doctor updateDoctor(Long doctorId, Doctor doctor);
    void deleteDoctor(Long doctorId);
    Doctor getDoctorById(Long doctorId);
    List<Doctor> getAllDoctors();
    List<Doctor> getDoctorsByHospital(Long hospitalId);
    List<Doctor> searchBySpecialization(String specialization);
    List<Doctor> getAvailableDoctors();
}
