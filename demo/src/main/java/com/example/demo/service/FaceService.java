package com.example.demo.service;


import com.example.demo.entity.UserFaceEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FaceService {

    /**
     * 人脸分析
     * @param fileBytes 从前端接收的文件二进制数组
     * @return User 人脸分析后生成的用户数据
     */
    public UserFaceEntity getUserFaceMessage(byte[] fileBytes) throws IOException;
}