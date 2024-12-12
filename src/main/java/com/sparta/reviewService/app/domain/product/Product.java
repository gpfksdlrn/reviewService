package com.sparta.reviewService.app.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 상품 id

    @Column(name = "reviewCount" , nullable = false)
    private Long reviewCount = 0L; // 총 리뷰 수

    @Column(name = "score" , nullable = false)
    private Float score = 0.0f; // 평균 점수

    public void addReview(int score) {
        this.reviewCount++;
        // 새로운 평균 = (기존 총점 + 새로운 점수) / 새로운 리뷰 수
        this.score = (this.score + (this.reviewCount - 1) + score) / this.reviewCount;
    }
}