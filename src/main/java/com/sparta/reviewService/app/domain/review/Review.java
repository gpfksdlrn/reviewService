package com.sparta.reviewService.app.domain.review;

import com.sparta.reviewService.app.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    public Review(Product product, Long userId, int score, String content, String imageUrl) {
        this.product = product;
        this.userId = userId;
        this.score = score;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
    }
}