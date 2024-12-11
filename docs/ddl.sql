CREATE TABLE IF NOT EXISTS `PRODUCT`
(
    `id`            BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID',
    `reviewCount`   BIGINT(20) NOT NULL DEFAULT 0 COMMENT '총 리뷰 수',
    `score`         FLOAT NOT NULL DEFAULT 0.0 COMMENT '평균 점수'
) ENGINE = InnoDB CHARSET = utf8 COMMENT = '상품 테이블';

CREATE TABLE IF NOT EXISTS `REVIEW`
(
    `id`        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT = '리뷰 ID',
    `productId` BIGINT NOT NULL COMMENT = '상품 ID',
    `userId`    BIGINT NOT NULL COMMENT = '유저 ID',
    `score`     TINYINT NOT NULL CHECK (`score` BETWEEN 1 AND 5) COMMENT = '리뷰 점수(1~5)',
    `content`   TEXT NOT NULL COMMENT = '리뷰 내용',
    `imageUrl`  VARCHAR(255) COMMENT = '이미지 URL',
    `createdAt` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT = '리뷰 작성 시간',
    UNIQUE (`productId`, `userId`) COMMENT = '동일 유저가 동일 상품에 대해 중복 리뷰를 작성하지 못하도록 제한'
    FOREIGN KEY (`productId`) REFERENCES `PRODUCT` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB CHARSET = utf8 COMMENT = '리뷰 테이블';

CREATE INDEX idx_review_product_createdAt
    ON Review(productId, createdAt DESC) COMMENT = '상품별 최신 리뷰 조회를 위한 복합 인덱스';