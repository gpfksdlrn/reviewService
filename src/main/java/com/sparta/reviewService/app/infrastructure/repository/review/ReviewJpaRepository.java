package com.sparta.reviewService.app.infrastructure.repository.review;

import com.sparta.reviewService.app.domain.review.Review;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    @Query(value = """
            SELECT r FROM Review r
            WHERE r.product.id = :productId
            ORDER BY r.createdAt DESC, r.id DESC
            """)
    Slice<Review> findFirstPageByProductId(@Param("productId") Long productId, Pageable pageable);


    @Query(value = """
            SELECT r FROM Review r
            WHERE r.product.id = :productId
            AND (r.createdAt < (SELECT r2.createdAt FROM Review r2 WHERE r2.id = :cursor)
                 OR (r.createdAt = (SELECT r2.createdAt FROM Review r2 WHERE r2.id = :cursor)
                     AND r.id < :cursor))
            ORDER BY r.createdAt DESC, r.id DESC
            """)
    Slice<Review> findByProductIdAndCursor(
            @Param("productId") Long productId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Review r " +
            "WHERE r.product.id = :productId AND r.userId = :userId")
    boolean existsByProductIdAndUserIdWithPessimisticLock(
            @Param("productId") Long productId,
            @Param("userId") Long userId
    );

    Optional<Review> findByProductIdAndUserId(Long productId, Long userId);
}
