package com.sparta.reviewService.app.interfaces.controller;

import com.sparta.reviewService.app.interfaces.res.ProductReviewRes;
import com.sparta.reviewService.app.interfaces.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상품 서비스 API", description = "상품 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class productController {

    private final ProductService productService;

    @GetMapping("/{productId}/reviews")
    public ProductReviewRes getReviews(
            @PathVariable Long productId,
            @RequestParam(required = false) Long cursor, // 커서 파라미터
            @RequestParam(defaultValue = "10") int size // 페이지 크기
    ) {
        return productService.getReviews(productId, cursor, size);
    }
}
