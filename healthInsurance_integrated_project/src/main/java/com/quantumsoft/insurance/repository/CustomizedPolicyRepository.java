package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.CustomizedPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedPolicyRepository extends JpaRepository<CustomizedPolicy, Long> {

}
