package com.sparta.reviewService.app.interfaces.controller;

import com.sparta.reviewService.app.domain.review.SelectReviewResult;
import com.sparta.reviewService.app.interfaces.req.ReviewReq;
import com.sparta.reviewService.app.interfaces.res.ReviewRes;
import com.sparta.reviewService.app.domain.review.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "상품 서비스 API", description = "상품 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}/reviews")
    public ReviewRes getReviews(
            @PathVariable Long productId,
            @RequestParam(required = false) Long cursor, // 커서 파라미터
            @RequestParam(defaultValue = "10") int size // 페이지 크기
    ) {
        SelectReviewResult result = reviewService.getReviews(productId, cursor, size);
        return ReviewRes.of(result);
    }

    @PostMapping("/{productId}/reviews")
    public ResponseEntity<Void> createReview(
            @PathVariable Long productId,
            @RequestBody ReviewReq reviewReq,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile
    ) {
        reviewService.createReview(productId, reviewReq.userId(), reviewReq.score(), reviewReq.content(), imageFile);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}