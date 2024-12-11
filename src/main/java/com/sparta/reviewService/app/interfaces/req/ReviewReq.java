package com.sparta.reviewService.app.interfaces.req;

public record ReviewReq(
        Long userId,    // 유저 ID
        int score,      // 리뷰 점수 (1~5)
        String content  // 리뷰 내용
) {
}