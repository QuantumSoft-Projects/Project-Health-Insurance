package com.quantumsoft.insurance.servicei;

import com.quantumsoft.insurance.entity.Review;

import java.util.List;

public interface ReviewService {
    Review submitReview(Review review);

    List<Review> getPolicyReviews(Long policyId);

    List<Review> getHospitalReviews(Long hospitalId);

    List<Review> getDoctorReviews(Long doctorId);

    Review editReview(Long reviewId, Review updatedReview);
}
