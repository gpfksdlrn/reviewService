package com.sparta.reviewService.app.interfaces.service;

import com.sparta.reviewService.app.interfaces.res.ProductReviewRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductService {
    public ProductReviewRes getReviews(Long productId, Long cursor, int size) {
        List<ProductReviewRes.Review> reviews = new ArrayList<>();
        ProductReviewRes.Review review = new ProductReviewRes.Review(
                15,
                1,
                5,
                "이걸 사용하고 제 인생이 달라졌습니다",
                "/image.png",
                "2024-11-25T00:00:000Z"
        );

        ProductReviewRes.Review review2 = new ProductReviewRes.Review(
                14,
                3,
                5,
                "이걸 사용하고 제 인생이 달라졌습니다",
                null,
                "2024-11-24T00:00:000Z"
        );

        reviews.add(review);
        reviews.add(review2);

        return new ProductReviewRes(15, 4.6f, 6, reviews);
    }
}
