package com.example.testBootOne.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testBootOne.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
//    @Select("select * from sys_user where delete_flg = 0")
//    List<UserEntity> findAllPage();
//
//    @Insert("insert into sys_user(username, password, nickname) values (#{username}, #{password}, #{nickname})")
//    Integer addUser(UserEntity user);
//
//    @Select("select * from sys_user where delete_flg = 0 and id = #{id}")
//    List<UserEntity> findById(Integer id);
//
//    Integer updateUser();
}
