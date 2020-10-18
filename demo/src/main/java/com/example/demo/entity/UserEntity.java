package com.example.demo.entity;

import lombok.Data;

@Data
public class UserEntity {
    private int uId;
    private String uName;
    private String uPassword;

    public UserEntity(String uName, String uPassword) {
        this.uName = uName;
        this.uPassword = uPassword;
    }
}
