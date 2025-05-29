package com.quantumsoft.insurance.repository;

import com.quantumsoft.insurance.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByUserAndPolicy(Users user, Policy policy);

    boolean existsByUserAndHospital(Users user, Hospital hospital);

    boolean existsByUserAndDoctor(Users user, Doctor doctor);

    List<Review> findByPolicy_PolicyId(Long policyId);

    List<Review> findByHospital_HospitalId(Long hospitalId);

    List<Review> findByDoctor_DoctorId(Long doctorId);

    public Review findByReviewId(Long reviewId);
}
