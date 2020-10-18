package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Select("select u_id,u_name,u_password from u_info where u_name = #{uName} AND u_password = #{uPassword}")
    UserEntity login(UserEntity userEntity);

    @Insert("insert into u_info(u_name,u_password) value(#{uName},#{uPassword})")
    @Options(useGeneratedKeys = true, keyProperty = "uId", keyColumn = "uId")
    int add(UserEntity userEntity);

}
