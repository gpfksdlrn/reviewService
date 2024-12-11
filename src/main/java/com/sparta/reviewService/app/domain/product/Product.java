package com.sparta.reviewService.app.domain.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}