package com.example.demo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserFaceEntity;
import com.example.demo.exception.MyErrorInfoException;

public class JsonUtils {

    private final static String girl = "Female";
    private final static String boy = "Male";

    /**
     * @param userFaceApiJson 从api获得的用户数据
     * @return 封装的  UserEntity
     * @throws IllegalArgumentException 性别错误
     */
    public static UserFaceEntity getUser(JSONObject userFaceApiJson) throws IllegalArgumentException {


        JSONArray facesArray = userFaceApiJson.getJSONArray("faces");
        if (facesArray.size() == 0) {
            throw new MyErrorInfoException("400", "图片无法识别到人脸");
        }
        JSONObject face = facesArray.getJSONObject(0);
        JSONObject attributes = face.getJSONObject("attributes");
        String getGender = String.valueOf(attributes.getJSONObject("gender").get("value"));
        String age = String.valueOf(attributes.getJSONObject("age").get("value"));
        String smileThreshold = String.valueOf(attributes.getJSONObject("smile").get("threshold"));
        String smileValue = String.valueOf(attributes.getJSONObject("smile").get("value"));

        int gender = -1;
        if (boy.equals(getGender)) {
            gender = 1;
        } else if (girl.equals(getGender)) {
            gender = 0;
        }
        return new UserFaceEntity(gender, age, smileThreshold, smileValue);

    }


}
