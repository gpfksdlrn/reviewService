package com.sparta.reviewService.app.domain.review;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    /**
     * 이미지 파일을 업로드하고 URL을 반환합니다.
     * @param imageFile 업로드할 이미지 파일
     * @return 업로드된 이미지의 URL
     */
    String uploadImage(MultipartFile imageFile);
}