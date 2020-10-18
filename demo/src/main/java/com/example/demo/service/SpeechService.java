package com.example.demo.service;

import com.example.demo.entity.AudioEntity;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

public interface SpeechService {

    public AudioEntity getAudioEntity(String message, String sessionId) throws TencentCloudSDKException;
}
