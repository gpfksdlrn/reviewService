package com.sparta.reviewService.app.interfaces.service;

import com.sparta.reviewService.app.domain.product.Product;
import com.sparta.reviewService.app.domain.product.ProductRepository;
import com.sparta.reviewService.app.domain.review.CreateReviewResult;
import com.sparta.reviewService.app.domain.review.ImageUploadService;
import com.sparta.reviewService.app.domain.review.Review;
import com.sparta.reviewService.app.domain.review.ReviewRepository;
import com.sparta.reviewService.app.interfaces.res.ReviewRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ImageUploadService imageUploadService;

    @Transactional(readOnly = true)
    public ReviewRes getReviews(Long productId, Long cursor, int size) {
        return null;
    }

    @Transactional
    public void createReview(Long productId, Long userId, int score, String content, MultipartFile imageFile) {
        // 1. 상품 검증
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));

        // 2. 중복 리뷰 검증
        boolean exists = reviewRepository.existsByProductIdAndUserId(productId, userId);
        if (exists) {
            throw new IllegalArgumentException("이미 해당 상품에 리뷰를 작성하였습니다.");
        }

        // 3. 이미지 업로드 (Dummy URL)
        String imageUrl = null;
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = imageUploadService.uploadImage(imageFile);
        }

        // 4. 리뷰 저장
        Review review = new Review(product, userId, score, content, imageUrl);
        reviewRepository.save(review);

        // 5. 상품 정보 업데이트
        product.addReview(score);
        productRepository.save(product);

        new CreateReviewResult(product.getId(), review.getUserId(), review.getScore(), review.getContent(), review.getCreatedAt());
    }
}
