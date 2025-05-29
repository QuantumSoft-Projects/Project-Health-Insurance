package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Hospital;
import com.quantumsoft.insurance.exception.HospitalNotFoundException;
import com.quantumsoft.insurance.repository.HospitalRepository;
import com.quantumsoft.insurance.servicei.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital updateHospital(Long id, Hospital updatedHospital) {
        Hospital existing = hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));

        existing.setName(updatedHospital.getName());
        existing.setAddress(updatedHospital.getAddress());
        existing.setCity(updatedHospital.getCity());
        existing.setState(updatedHospital.getState());
        existing.setPostalCode(updatedHospital.getPostalCode());
        existing.setContactNumber(updatedHospital.getContactNumber());
        existing.setEmail(updatedHospital.getEmail());
        existing.setWebsite(updatedHospital.getWebsite());
        existing.setNetworkHospital(updatedHospital.isNetworkHospital());
        existing.setAvailableBeds(updatedHospital.getAvailableBeds());
        existing.setIcuAvailable(updatedHospital.getIcuAvailable());
        existing.setIsActive(updatedHospital.getIsActive());
        existing.setSpecialties(updatedHospital.getSpecialties());

        return hospitalRepository.save(existing);
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found with id: " + id));
    }

    @Override
    public void deleteHospital(Long id) {
        Hospital hospital = getHospitalById(id);
        hospitalRepository.delete(hospital);
    }

    @Override
    public List<Hospital> getAllHospitals() {

        return hospitalRepository.findAll();
    }

    @Override
    public List<Hospital> getHospitalsByCity(String city) {

        return hospitalRepository.findByCityIgnoreCase(city);
    }

    @Override
    public List<Hospital> getNetworkHospitals() {

        return hospitalRepository.findByIsNetworkHospitalTrue();
    }

    @Override
    public List<Hospital> getHospitalsWithICU() {

        return hospitalRepository.findByIcuAvailableTrue();
    }
}
