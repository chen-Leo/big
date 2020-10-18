package com.example.demo.entity;

import com.tencentcloudapi.tts.v20190823.models.TextToVoiceResponse;
import lombok.Data;

@Data
public class AudioEntity {
    private String sessionID;
    private String audio;
    private String requestId;

    public AudioEntity(TextToVoiceResponse text) {
        this.sessionID = text.getSessionId();
        this.audio = text.getAudio();
        this.requestId = text.getRequestId();
    }
}
