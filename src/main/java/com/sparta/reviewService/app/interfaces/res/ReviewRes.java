package com.sparta.reviewService.app.interfaces.res;

import com.sparta.reviewService.app.domain.review.SelectReviewResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record ReviewRes(
        long totalCount, // 해당 상품에 작성된 총 리뷰 수
        float score,    // 평균 점수
        Long cursor,
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

    public static ReviewRes of(SelectReviewResult result) {
        List<Review> reviewList = new ArrayList<>();

        for(SelectReviewResult.Review review : result.reviews()) {
            reviewList.add(new Review(
                    review.id(),
                    review.userId(),
                    review.score(),
                    review.content(),
                    review.imageUrl(),
                    review.createdAt()
            ));
        }

        return new ReviewRes(
                result.totalCount(),
                result.score(),
                result.cursor(),
                reviewList
        );
    }
}