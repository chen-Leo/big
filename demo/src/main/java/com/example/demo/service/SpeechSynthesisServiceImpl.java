package com.example.demo.service;

import com.example.demo.entity.AudioEntity;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tts.v20190823.TtsClient;
import com.tencentcloudapi.tts.v20190823.models.TextToVoiceRequest;
import com.tencentcloudapi.tts.v20190823.models.TextToVoiceResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SpeechSynthesisServiceImpl implements SpeechService {

    @Override
    public AudioEntity getAudioEntity(String message, String sessionId) throws TencentCloudSDKException {

        Credential cred = new Credential("AKIDIVuMYSRdngpkPoorXy9VcU8VbVBamo6B", "71jS293pLmyuc3RbWBZAWbO7bqnbkjSv");
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tts.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        TtsClient client = new TtsClient(cred, "ap-chongqing", clientProfile);
        TextToVoiceRequest req = new TextToVoiceRequest();

        req.setText(message);
        req.setSessionId(sessionId);
        req.setModelType(1L);
        TextToVoiceResponse resp = client.TextToVoice(req);

        return new AudioEntity(resp);
    }
}
