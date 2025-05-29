package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByHospital_HospitalId(Long hospitalId);
    List<Doctor> findBySpecializationIgnoreCase(String specialization);

    List<Doctor> findByAvailableTrue();
}