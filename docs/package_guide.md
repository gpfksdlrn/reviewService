## 프로젝트 디렉토리 구조 ##
```shell
 app
  └─ domain         # 핵심 비즈니스 로직 및 엔티티
  │   └─ product                    # 상품 도메인
  │   │   └─ Product.java           # 상품 엔티티 클래스
  │   │   └─ ProductRepository.java # 상품 관련 Repository 인터페이스
  │   └─ review                     # 리뷰 도메인
  │       └─ Review.java            # 리뷰 엔티티 클래스
  │       └─ ReviewRepository.java  # 리뷰 관련 Repository 인터페이스
  │
  └─ infrastructure # 데이터베이스 및 외부 시스템과의 통신
  │   └─ repository     # 데이터베이스와의 통신을 담당하는 레이어
  │       └─ product          # 상품 관련 Repository 구현체
  │       │   └─ ProductJpaRepository.java  # 상품 데이터베이스 통신 JPA 구현체
  │       │   └─ ProductRepositoryImpl.java # 상품 Repository 커스텀 구현체 (필요 시 작성)
  │       └─ review           # 리뷰 관련 Repository 구현체
  │           └─ ReviewJpaRepository.java   # 리뷰 데이터베이스 통신 JPA 구현체
  │           └─ ReviewRepositoryImpl.java  # 리뷰 Repository 커스텀 구현체 (필요 시 작성)
  │
  └─ interfaces     # API 요청/응답 및 컨트롤러
  │   └─ controller     # API 요청을 처리하는 HTTP Controller
  │   │   └─ ProductController.java   # 상품 및 리뷰 API 컨트롤러
  │   └─ req            # 요청(Request) DTO
  │   │   └─ ReviewReq.java           # 리뷰 등록 요청 DTO
  │   └─ res            # 응답(Response) DTO
  │       └─ ReviewRes.java           # 리뷰 조회 응답 DTO
  │
  └─ service        # 비즈니스 로직을 처리하는 레이어
      └─ ProductService.java   # 상품 관련 비즈니스 로직
      └─ ReviewService.java    # 리뷰 관련 비즈니스 로직

```