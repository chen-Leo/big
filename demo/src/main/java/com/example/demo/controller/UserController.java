package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.returnJson.ReturnJsonEntity;
import com.example.demo.exception.MyErrorInfoException;
import com.example.demo.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;



@Controller
public class UserController {


    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/")
    public String home() {
        System.out.println();
        return "login";

    }



    @RequestMapping(value = "/user/registered", method = RequestMethod.POST)
    @ResponseBody
    public ReturnJsonEntity registered(String username, String password) {
        UserEntity userEntity;
        try {
            userEntity = new UserEntity(username, password);
            userService.add(userEntity);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            throw new MyErrorInfoException("400", "用户名重复");
        }
        userEntity.setUPassword(" ");
        return new ReturnJsonEntity("200", userEntity);
    }


    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody

    public ReturnJsonEntity login(HttpServletRequest request, String username, String password) throws Exception {

        UserEntity userEntity = new UserEntity(username, password);
        userEntity = userService.login(request, userEntity);
        if (userEntity == null) {
            throw new MyErrorInfoException("400", "用户名或密码错误");
        }
        return new ReturnJsonEntity("200", userEntity);
    }


    @RequestMapping(value = "/user/login")
    public String login() {
        System.out.println();
        return "login";

    }

    @RequestMapping(value = "/user/photo")
    public String photo() {
        return "photo";

    }
}
