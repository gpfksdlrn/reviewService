package com.sparta.reviewService.app.domain.review;

// 리뷰 데이터를 관리하는 인터페이스
public interface ReviewRepository {
    boolean existsByProductIdAndUserId(Long productId, Long aLong);
    void save(Review review);
}
