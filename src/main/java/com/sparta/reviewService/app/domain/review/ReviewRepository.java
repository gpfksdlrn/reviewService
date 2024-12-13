package com.sparta.reviewService.app.domain.review;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

// 리뷰 데이터를 관리하는 인터페이스
public interface ReviewRepository {
    void save(Review review);
    Slice<Review> findFirstPageByProductId(Long productId, PageRequest of);
    Slice<Review> findByProductIdAndCursor(Long productId, Long cursor, PageRequest of);
    boolean existsByProductIdAndUserIdWithPessimisticLock(Long productId, Long userId);
}
