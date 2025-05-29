package com.quantumsoft.insurance.repository;
import com.quantumsoft.insurance.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByCityIgnoreCase(String city);
    List<Hospital> findByIsNetworkHospitalTrue();
    List<Hospital> findByIcuAvailableTrue();
}
