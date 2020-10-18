package com.example.demo.service;

import com.example.demo.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {

    UserEntity add (UserEntity userEntity) throws SQLIntegrityConstraintViolationException, NoSuchAlgorithmException;

    UserEntity login(HttpServletRequest request,UserEntity userEntity) throws Exception;
}
