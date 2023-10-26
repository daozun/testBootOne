package com.example.testBootOne.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.testBootOne.entity.UserEntity;
import com.example.testBootOne.mapper.UserMapper;
import com.example.testBootOne.utils.JwtTokenUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;

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

    public String handleLogin(UserEntity userEntity) {
        if(userEntity.getUsername() == null || userEntity.getPassword() == null) {
            return "请输入用户名或密码";
        }

        String username = userEntity.getUsername();
        String password = userEntity.getPassword();

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        UserEntity userObj =  userMapper.selectOne(wrapper);

        if(ObjectUtil.isNotEmpty(userObj)) {
            if(encoder.matches(password, userObj.getPassword())) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", userObj);
                return jwtTokenUtil.generateToken(map);
            }
            return "密码错误";
        }

        return "用户名错误";
    }

    public String handleRegister(UserEntity userEntity) {
        if(userEntity.getUsername() == null || userEntity.getPassword() == null) {
            return "请输入用户名或密码";
        }

        String username = userEntity.getUsername();
        String password = userEntity.getPassword();

        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        UserEntity userObj =  userMapper.selectOne(wrapper);

        if(ObjectUtil.isNotEmpty(userObj)) {
            return "用户名重复";
        }

        userEntity.setUsername(username);
        userEntity.setNickname(username);
        userEntity.setPassword(encoder.encode(password));

        int isSave = userMapper.insert(userEntity);

        if(isSave == 1) {
            return "新增成功";
        }

        return "新增失败";
    }
}
