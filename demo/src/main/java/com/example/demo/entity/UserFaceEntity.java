package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFaceEntity implements Serializable {

    /**
     * 性别 gender = 1是男性 ，gender = 0 是女性
     */
    private int gender;
    private int age;
    private String smileMessage;
    private String message;


    public UserFaceEntity() {
    }

    public UserFaceEntity(int gender, String age, String smileThreshold, String smileValue) {
        this.gender = gender;
        this.age = Integer.parseInt(age);

        double reference = Double.parseDouble(smileThreshold);
        double smile = Double.parseDouble(smileValue) - reference;
        if (smile >= 25) {
            this.smileMessage = "你似乎很开心，有什么好事发生吗";
        } else if (smile > 0) {
            this.smileMessage = "你心情不错";
        } else if (smile == 0) {
            this.smileMessage = "你应该笑一笑的";
        } else if (smile >= -25) {
            this.smileMessage = "你今天似乎心情不佳";
        } else {
            this.smileMessage = "你似乎生气了，生气不是一件好事";
        }

        String userGender;
        if (gender == 1) {
            userGender = "帅气的";
            if( Integer.parseInt(age) > 18) {
                userGender = userGender +"男性";
            } else  {
                userGender = userGender +"少年";
            }
        } else {
            userGender = "漂亮的";
            if( Integer.parseInt(age) > 18) {
                userGender = userGender +"女性";
            } else {
                userGender = userGender +"女孩";
            }
        }

        this.message = "你是一位" + userGender + "，我猜测你大概" + this.age + "岁。" + this.smileMessage;

    }

}
