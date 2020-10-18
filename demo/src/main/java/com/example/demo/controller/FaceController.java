package com.example.demo.controller;


import com.example.demo.entity.AudioEntity;
import com.example.demo.entity.FaceAudioEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserFaceEntity;
import com.example.demo.entity.returnJson.ReturnJsonEntity;
import com.example.demo.exception.MyErrorInfoException;
import com.example.demo.service.FaceServiceImpl;
import com.example.demo.service.SpeechSynthesisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


@Controller
public class FaceController {

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    FaceServiceImpl faceRecognitionService;

    @Autowired
    SpeechSynthesisServiceImpl speechSynthesisService;


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonEntity getAI(HttpSession session, @RequestParam("file") MultipartFile upload) throws Exception {
        if (session == null || session.getAttribute(session.getId()) == null) {
            throw new MyErrorInfoException("400", "用户未登录");
        }
        if (upload == null || upload.isEmpty()) {
            throw new MyErrorInfoException("400", "文件为空");
        }

        UserEntity userEntity = (UserEntity) session.getAttribute(session.getId());

        byte[] fileBytes = upload.getInputStream().readAllBytes();
        String hashcode = userEntity.getUName() + Arrays.hashCode(fileBytes);

        FaceAudioEntity faceAudioEntity;
        faceAudioEntity = (FaceAudioEntity) redisTemplate.opsForValue().get(hashcode);
        if (faceAudioEntity != null) {
            System.out.println("get redis");
            return new ReturnJsonEntity("200", faceAudioEntity);
        }
        System.out.println("not get redis");

        UserFaceEntity myUser;
        myUser = faceRecognitionService.getUserFaceMessage(fileBytes);
        AudioEntity audio = speechSynthesisService.getAudioEntity(myUser.getMessage(), session.getId());
        faceAudioEntity = new FaceAudioEntity(myUser, audio.getAudio());

        redisTemplate.opsForValue().set(hashcode, faceAudioEntity, 60 * (1 + (int) (Math.random() * 5)), TimeUnit.SECONDS);

        return new ReturnJsonEntity("200", faceAudioEntity);
    }
}
