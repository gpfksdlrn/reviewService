package com.sparta.reviewService.app.domain.review;

import com.sparta.reviewService.app.domain.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReviewUnitTest {

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product(1L, 10L, 2.6f);  // 상품 mock
    }

    @Nested
    @DisplayName("리뷰 등록 테스트")
    class CreateReviewTests {

        @Test
        @DisplayName("리뷰 등록 테스트")
        void createReview() {
            // given
            Long userId = 1L;
            int score = 3;
            String content = "별로에요";
            String imageUrl = "";

            // when
            Review review = new Review(
                    product, userId, score, content, imageUrl
            );

            // then
            assertAll(
                    () -> assertThat(review.getProduct()).isEqualTo(product),
                    () -> assertThat(review.getUserId()).isEqualTo(userId),
                    () -> assertThat(review.getScore()).isEqualTo(score),
                    () -> assertThat(review.getContent()).isEqualTo(content),
                    () -> assertThat(review.getImageUrl()).isEqualTo(imageUrl)
            );
        }
    }
}