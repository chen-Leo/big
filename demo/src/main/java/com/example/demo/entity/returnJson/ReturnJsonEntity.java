package com.example.demo.entity.returnJson;

import lombok.Data;

@Data
public class ReturnJsonEntity {
    private String code;
    private Object message;

    public ReturnJsonEntity(String code, Object message) {
        this.code = code;
        this.message = message;
    }

}
