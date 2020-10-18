package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FaceAudioEntity implements Serializable {

    private UserFaceEntity userFaceEntity;
    private String audio;

    public FaceAudioEntity(UserFaceEntity userFaceEntity, String audio) {
        this.userFaceEntity = userFaceEntity;
        this.audio = audio;
    }
}
