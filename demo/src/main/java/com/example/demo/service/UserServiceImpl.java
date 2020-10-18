package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.exception.MyErrorInfoException;
import com.example.demo.mapper.UserMapper;

import com.example.demo.utils.SecurityUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserEntity add(UserEntity userEntity) {

        try {
            userEntity.setUPassword(SecurityUtils.passwordHash(userEntity.getUPassword()));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密参数错误");
            throw new MyErrorInfoException("500", "服务器错误");
        }
        userMapper.add(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity login(HttpServletRequest request, UserEntity userEntity)  {
        try {
            userEntity.setUPassword(SecurityUtils.passwordHash(userEntity.getUPassword()));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("加密参数错误");
            throw new MyErrorInfoException("500", "服务器错误");
        }
        userEntity = userMapper.login(userEntity);
        if (userEntity == null) {
            return null;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(session.getId(), userEntity);
        userEntity.setUPassword(" ");
        return userEntity;
    }
}
