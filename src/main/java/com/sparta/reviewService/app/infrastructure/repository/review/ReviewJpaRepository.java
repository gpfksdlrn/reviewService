package com.sparta.reviewService.app.infrastructure.repository.review;

import com.sparta.reviewService.app.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
}
