package com.sparta.reviewService.app.domain.product;

// 상품 데이터를 관리하는 인터페이스
public interface ProductRepository {
    Product findById(Long productId); // 상품 ID로 조회
    void save(Product product);
    Product findByIdWithPessimisticLock(Long productId);
}
