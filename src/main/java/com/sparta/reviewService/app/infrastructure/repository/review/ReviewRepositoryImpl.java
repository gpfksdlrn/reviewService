package com.sparta.reviewService.app.infrastructure.repository.review;

import com.sparta.reviewService.app.domain.review.Review;
import com.sparta.reviewService.app.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository jpaRepository;

    @Override
    public boolean existsByProductIdAndUserId(Long productId, Long userId) {
        return existsByProductIdAndUserId(productId, userId);
    }

    @Override
    public void save(Review review) {
        jpaRepository.save(review);
    }
}
