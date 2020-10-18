package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {
    static final String strType = "SHA-256";

    public static String passwordHash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(strType);
        messageDigest.update(password.getBytes());
        byte[] byteBuffer = messageDigest.digest();
        return Base64.getEncoder().encodeToString(byteBuffer);
    }
}
