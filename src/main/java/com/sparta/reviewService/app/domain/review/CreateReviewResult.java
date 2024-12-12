package com.sparta.reviewService.app.domain.review;

import java.time.LocalDateTime;

public record CreateReviewResult(
        long productId,
        long userId,
        int score,  // 1 ~ 5 사이
        String content,
        LocalDateTime createdAt
) {
}