package com.sparta.reviewService.app.infrastructure.repository.product;

import com.sparta.reviewService.app.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
}