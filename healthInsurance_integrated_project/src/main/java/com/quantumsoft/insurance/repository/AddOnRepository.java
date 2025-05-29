package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.AddOn;
import com.quantumsoft.insurance.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Long> {
}
