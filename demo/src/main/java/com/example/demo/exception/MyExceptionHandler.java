package com.example.demo.exception;

import com.example.demo.entity.returnJson.ReturnJsonEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = MyErrorInfoException.class)
    @ResponseBody
    public ReturnJsonEntity bizExceptionHandler(MyErrorInfoException e) {
        System.out.println(e.getErrorMsg());
        return new ReturnJsonEntity(e.getErrorCode(), e.getErrorMsg());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReturnJsonEntity bizExceptionHandler(Exception e) {
        System.out.println(e);
        return new ReturnJsonEntity("500", "服务器内部错误");
    }
}
