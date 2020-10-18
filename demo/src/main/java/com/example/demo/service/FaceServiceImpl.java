package com.example.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserFaceEntity;
import com.example.demo.service.FaceService;
import com.example.demo.utils.JsonUtils;
import org.apache.ibatis.javassist.Loader;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;


@Service
public class FaceServiceImpl implements FaceService {


    /**
     * 人脸分析api
     */
    final String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";


    @Override
    public UserFaceEntity getUserFaceMessage(byte[] fileBytes) throws IOException {

        String data = Base64.getEncoder().encodeToString(fileBytes);
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        //需要填入旷视人脸识别接口
        body.add("api_key", "");
        body.add("api_secret", "");
        body.add("image_base64", data);
        body.add("return_attributes", "gender,age,smiling");

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        HttpEntity httpEntity = new HttpEntity(body, headers);

        ResponseEntity<String> returnBody = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        JSONObject userFaceApiJson = JSONObject.parseObject(returnBody.getBody());

        return JsonUtils.getUser(userFaceApiJson);

    }


}
