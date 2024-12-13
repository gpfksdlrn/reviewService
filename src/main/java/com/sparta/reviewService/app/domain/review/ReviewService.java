package com.sparta.reviewService.app.domain.review;

import com.sparta.reviewService.app.domain.product.Product;
import com.sparta.reviewService.app.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReviewService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final ImageUploadService imageUploadService;

    @Transactional(readOnly = true)
    public SelectReviewResult getReviews(Long productId, Long cursor, int size) {
        Product product = productRepository.findById(productId);

        Slice<Review> reviewSlice;
        if (cursor == null) {
            reviewSlice = reviewRepository.findFirstPageByProductId(productId, PageRequest.of(0, size + 1));
        } else {
            reviewSlice = reviewRepository.findByProductIdAndCursor(productId, cursor, PageRequest.of(0, size + 1));
        }

        List<Review> content = reviewSlice.getContent();
        boolean hasNext = content.size() > size;
        if (hasNext) {
            content.remove(content.size() - 1);
        }

        List<SelectReviewResult.Review> reviews = content.stream()
                .map(review -> new SelectReviewResult.Review(
                        review.getId(),
                        review.getUserId(),
                        review.getScore(),
                        review.getContent(),
                        review.getImageUrl(),
                        review.getCreatedAt()
                ))
                .collect(Collectors.toList());

        Long nextCursor = reviews.isEmpty() ? null : reviews.get(reviews.size() - 1).id();

        return new SelectReviewResult(
                product.getReviewCount(),
                product.getScore(),
                nextCursor,
                reviews
        );
    }


    @Transactional
    public void createReview(Long productId, Long userId, int score, String content, MultipartFile imageFile) {
        Product product = productRepository.findByIdWithPessimisticLock(productId);
        boolean exists = reviewRepository.existsByProductIdAndUserIdWithPessimisticLock(productId, userId);

        if (exists) {
            throw new IllegalArgumentException("이미 해당 상품에 리뷰를 작성하였습니다.");
        }

        String imageUrl = "https://dummyimage.com/600x400/000/fff.png";
        if (imageFile != null && !imageFile.isEmpty()) {
            imageUrl = imageUploadService.uploadImage(imageFile);
        }

        Review review = new Review(product, userId, score, content, imageUrl);
        reviewRepository.save(review);

        product.addReview(score);
        productRepository.save(product);
    }
}
