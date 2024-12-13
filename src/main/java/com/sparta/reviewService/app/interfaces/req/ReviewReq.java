package com.sparta.reviewService.app.interfaces.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record ReviewReq(
        @Schema(description = "유저 id", defaultValue = "1")
        Long userId,    // 유저 ID

        @Schema(description = "리뷰점수", allowableValues = "1,2,3,4,5", defaultValue = "0")
        @Min(value = 1, message = "score는 1 이상이여야 합니다.")
        @Max(value = 5, message = "score는 5 이하여야 합니다.")
        int score,      // 리뷰 점수 (1~5)

        @Schema(description = "리뷰내용", defaultValue = "content")
        String content  // 리뷰 내용
) {
}