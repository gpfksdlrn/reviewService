package com.sparta.reviewService.app.domain.review;

import com.sparta.reviewService.app.domain.IntegrationTest;
import com.sparta.reviewService.app.domain.product.Product;
import com.sparta.reviewService.app.domain.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class ReviewServiceTest extends IntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private ReviewService reviewService;

    @Test
    @DisplayName("리뷰 등록 후 성공 테스트")
    void review_insert_success_test() {
        // given
        long productId = 1L;
        long userId = 112L;
        int score = 4;
        String content = "테스트 콘텐츠";
        productRepository.save(new Product(productId, 1L, 1f));

        // when
        reviewService.createReview(productId, userId, score, content, null);
        Review review = reviewRepository.findByProductIdAndUserId(productId, userId);


        // then
        assertThat(review).isNotNull();
        assertAll(
                () -> assertThat(review.getProduct().getId()).isEqualTo(productId),
                () -> assertThat(review.getUserId()).isEqualTo(userId),
                () -> assertThat(review.getScore()).isEqualTo(score),
                () -> assertThat(review.getContent()).isEqualTo(content)
        );
    }
}