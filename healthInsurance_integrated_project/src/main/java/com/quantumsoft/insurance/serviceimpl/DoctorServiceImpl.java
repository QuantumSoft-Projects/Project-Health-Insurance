package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Doctor;
import com.quantumsoft.insurance.entity.Hospital;
import com.quantumsoft.insurance.exception.ResourceNotFoundException;
import com.quantumsoft.insurance.repository.DoctorRepository;
import com.quantumsoft.insurance.repository.HospitalRepository;
import com.quantumsoft.insurance.servicei.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Doctor createDoctor(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with ID: " + hospitalId));
        doctor.setHospital(hospital);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long doctorId, Doctor doctorDetails) {
        Doctor doctor = getDoctorById(doctorId);
        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setAvailable(doctorDetails.isAvailable());
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        doctorRepository.delete(doctor);
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsByHospital(Long hospitalId) {
        return doctorRepository.findByHospital_HospitalId(hospitalId);
    }

    @Override
    public List<Doctor> searchBySpecialization(String specialization) {
        return doctorRepository.findBySpecializationIgnoreCase(specialization);
    }

    @Override
    public List<Doctor> getAvailableDoctors() {
        return doctorRepository.findByAvailableTrue();
    }
}
