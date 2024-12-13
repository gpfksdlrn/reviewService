package com.sparta.reviewService.app.infrastructure.repository.review;

import com.sparta.reviewService.app.domain.review.Review;
import com.sparta.reviewService.app.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository jpaRepository;

    @Override
    public void save(Review review) {
        jpaRepository.save(review);
    }

    @Override
    public Slice<Review> findFirstPageByProductId(Long productId, PageRequest of) {
        return jpaRepository.findFirstPageByProductId(productId, of);
    }

    @Override
    public Slice<Review> findByProductIdAndCursor(Long productId, Long cursor, PageRequest of) {
        return jpaRepository.findByProductIdAndCursor(productId, cursor, of);
    }

    @Override
    public boolean existsByProductIdAndUserIdWithPessimisticLock(Long productId, Long userId) {
        return jpaRepository.existsByProductIdAndUserIdWithPessimisticLock(productId, userId);
    }

    @Override
    public Review findByProductIdAndUserId(Long productId, Long userId) {
        return jpaRepository.findByProductIdAndUserId(productId, userId).orElseThrow(() ->
                new NoSuchElementException("Product with id " + productId + " does not exist"));
    }
}
