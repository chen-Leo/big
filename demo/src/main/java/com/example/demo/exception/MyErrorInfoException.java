package com.example.demo.exception;

import lombok.Data;

@Data
public class MyErrorInfoException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public MyErrorInfoException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
