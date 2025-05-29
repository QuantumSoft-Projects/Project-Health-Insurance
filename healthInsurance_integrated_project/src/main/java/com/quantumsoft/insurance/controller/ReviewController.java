    package com.quantumsoft.insurance.controller;

    import com.quantumsoft.insurance.entity.Review;
    import com.quantumsoft.insurance.exception.UnAuthorizedAccessException;
    import com.quantumsoft.insurance.repository.ReviewRepository;
    import com.quantumsoft.insurance.servicei.ReviewService;
    import com.quantumsoft.insurance.serviceimpl.JwtService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/reviews")
    @RequiredArgsConstructor
    @CrossOrigin("*")
    public class ReviewController {

        private final ReviewService reviewService;

        @Autowired
        private JwtService jwtService;

        @PostMapping
        public ResponseEntity<Review> submitReview(@RequestBody Review review) {
            return ResponseEntity.ok(reviewService.submitReview(review));
        }

        @PutMapping("/{reviewId}")
        public ResponseEntity<Review> editReview(@PathVariable Long reviewId, @RequestBody Review updatedReview, @RequestHeader("Authorization")String bearerToken) {
            String token = bearerToken.substring(7);
            String role = jwtService.extractRole(token);
            System.out.println("Role: " + role);
            if (role.equals("USER")){
                System.out.println("role verified");
                Review review = reviewService.editReview(reviewId, updatedReview);
                return ResponseEntity.ok(review);
            } else
                throw new UnAuthorizedAccessException("Unauthorized Access");
        }

        @GetMapping("/policy/{policyId}")
        public ResponseEntity<List<Review>> getPolicyReviews(@PathVariable Long policyId) {
            return ResponseEntity.ok(reviewService.getPolicyReviews(policyId));
        }

        @GetMapping("/hospital/{hospitalId}")
        public ResponseEntity<List<Review>> getHospitalReviews(@PathVariable Long hospitalId) {
            return ResponseEntity.ok(reviewService.getHospitalReviews(hospitalId));
        }

        @GetMapping("/doctor/{doctorId}")
        public ResponseEntity<List<Review>> getDoctorReviews(@PathVariable Long doctorId) {
            return ResponseEntity.ok(reviewService.getDoctorReviews(doctorId));
        }
    }
