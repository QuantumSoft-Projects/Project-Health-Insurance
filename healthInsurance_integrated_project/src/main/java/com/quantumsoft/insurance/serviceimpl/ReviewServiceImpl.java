package com.quantumsoft.insurance.serviceimpl;

import com.quantumsoft.insurance.entity.Review;
import com.quantumsoft.insurance.repository.ReviewRepository;
import com.quantumsoft.insurance.servicei.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review submitReview(Review review) {

        if(review.getPolicyId()!=null &&
                reviewRepository.existsByUserAndPolicy(review.getUser(), review.getPolicy())) {
            throw new IllegalArgumentException("You have already reviewed this policy");
        }

        if (review.getHospitalId() != null &&
                reviewRepository.existsByUserAndHospital(review.getUser(), review.getHospital())){
            throw new IllegalArgumentException("You have already reviewed this hospital");
        }

        if (review.getDoctorId() != null &&
                reviewRepository.existsByUserAndDoctor(review.getUser(), review.getDoctor())) {
            throw new IllegalArgumentException("You have already reviewed this doctor");
        }

        // TODO: Validate user has actually used the service (via policy/hospital/consultation history)

        review.setReviewDate(LocalDateTime.now());

        return reviewRepository.save(review);

    }

    @Override
    public Review editReview(Long reviewId, Review updatedReview) {
        Review existingReview = reviewRepository.findByReviewId(reviewId);
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            //existingReview.setReviewDate(LocalDateTime.now()); // Update timestamp

            return reviewRepository.save(existingReview);
    }

    @Override
    public List<Review> getPolicyReviews(Long policyId) {
        return reviewRepository.findByPolicy_PolicyId(policyId);
    }

    @Override
    public List<Review> getHospitalReviews(Long hospitalId) {
        return reviewRepository.findByHospital_HospitalId(hospitalId);
    }

    @Override
    public List<Review> getDoctorReviews(Long doctorId) {
        return reviewRepository.findByDoctor_DoctorId(doctorId);
    }


}
