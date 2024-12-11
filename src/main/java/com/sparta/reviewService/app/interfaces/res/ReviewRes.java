package com.sparta.reviewService.app.interfaces.res;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewRes(
        long totalCount, // 해당 상품에 작성된 총 리뷰 수
        float score,    // 평균 점수
        long cursor,
        List<Review> reviews
) {
    public record Review(
        long id,
        long userId,
        int score,  // 1 ~ 5 사이
        String content,
        String imageUrl,
        LocalDateTime createdAt
    ){}
}