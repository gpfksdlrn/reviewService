package com.sparta.reviewService.app.infrastructure.upload;

import com.sparta.reviewService.app.domain.review.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Override
    public String uploadImage(MultipartFile imageFile) {
        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        try {
            // 파일 이름에서 확장자 추출
            String originalFilename = imageFile.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 현재 날짜를 이용한 폴더 구조 생성
            LocalDateTime now = LocalDateTime.now();
            String datePath = String.format("%d/%02d/%02d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());

            // 현재 시간을 기반으로 한 고유한 파일명 생성
            String uniqueFileName = System.currentTimeMillis() + "." + fileExtension;

            // 최종 URL 생성 (reviews/2024/03/12/filename.jpg 형식)
            return String.format("https://dummy-s3-bucket.amazone.com/reviews/%s/%s", datePath, uniqueFileName);

        } catch (Exception e) {
            throw new RuntimeException("이미지 업로드 중 오류가 발생했습니다.");
        }
    }
}
