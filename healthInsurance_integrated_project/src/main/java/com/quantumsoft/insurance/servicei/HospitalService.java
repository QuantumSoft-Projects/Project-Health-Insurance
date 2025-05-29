package com.quantumsoft.insurance.servicei;
import com.quantumsoft.insurance.entity.Hospital;

import java.util.List;

public interface HospitalService {
    Hospital addHospital(Hospital hospital);
    Hospital updateHospital(Long id, Hospital hospital);
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    List<Hospital> getAllHospitals();
    List<Hospital> getHospitalsByCity(String city);
    List<Hospital> getNetworkHospitals();
    List<Hospital> getHospitalsWithICU();
}
