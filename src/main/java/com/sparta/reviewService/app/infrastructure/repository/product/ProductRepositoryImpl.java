package com.sparta.reviewService.app.infrastructure.repository.product;

import com.sparta.reviewService.app.domain.product.Product;
import com.sparta.reviewService.app.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository jpaRepository;

    @Override
    public Product findById(Long productId) {
        return jpaRepository.findById(productId).orElseThrow(()
                -> new NullPointerException("상품이 존재하지 않습니다."));
    }

    @Override
    public void save(Product product) {
        jpaRepository.save(product);
    }

    @Override
    public Product findByIdWithPessimisticLock(Long productId) {
        return jpaRepository.findByIdWithPessimisticLock(productId);
    }
}
