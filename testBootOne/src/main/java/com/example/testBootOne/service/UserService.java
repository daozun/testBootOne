package com.example.testBootOne.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testBootOne.entity.UserEntity;
import com.example.testBootOne.mapper.UserMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    @Autowired
    private UserMapper userMapper;

    public Integer addOrUser(@NotNull UserEntity userEntity) {
        if(userEntity.getId() == null) {
            return userMapper.insert(userEntity);
        } else {
            return userMapper.updateById(userEntity);
        }
    }

    public List<UserEntity> getAllPage() {
        return userMapper.selectList(null);
    }

    public List<UserEntity> findUserById(Integer id) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return userMapper.selectList(wrapper);
    }

    public IPage<UserEntity> selectPage(Integer pageNum, Integer pageSize) {
        return page(new Page<>(pageNum, pageSize));
    }

    public IPage<UserEntity> findByCondition(Integer pageNum, Integer pageSize, UserEntity user) {
        LambdaQueryWrapper<UserEntity> query = Wrappers.lambdaQuery(UserEntity.class);

        query.eq(user.getId() != null && user.getId() > 0, UserEntity::getId, user.getId());
        query.like(StrUtil.isNotEmpty(user.getNickname()), UserEntity::getNickname, user.getNickname());
        query.like(StrUtil.isNotEmpty(user.getUsername()), UserEntity::getUsername, user.getUsername());

        return page(new Page<>(pageNum, pageSize), query);

    }
}
