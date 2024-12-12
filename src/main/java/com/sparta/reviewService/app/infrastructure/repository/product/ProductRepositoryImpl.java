package com.sparta.reviewService.app.infrastructure.repository.product;

import com.sparta.reviewService.app.domain.product.Product;
import com.sparta.reviewService.app.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;

    @Override
    public Optional<Product> findById(Long productId) {
        return jpaRepository.findById(productId);
    }

    @Override
    public void save(Product product) {
        jpaRepository.save(product);
    }
}
