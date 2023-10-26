package com.example.testBootOne.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.testBootOne.entity.Result;
import com.example.testBootOne.entity.UserEntity;
import com.example.testBootOne.mapper.UserMapper;
import com.example.testBootOne.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @PostMapping
    public Integer add(@RequestBody UserEntity userEntity) {
        return userService.addOrUser(userEntity);
    }

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.getAllPage();
    }

    @GetMapping("/findById")
    public List<UserEntity> findById(@RequestParam(value="id") Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/findSelectPage")
    public IPage<UserEntity> findSelectPage(@RequestParam(value="pageNum") Integer pageNum, @RequestParam(value="pageSize") Integer pageSize) {
        return userService.selectPage(pageNum, pageSize);
    }

    @GetMapping("/findByCondition")
    public IPage<UserEntity> findByCondition(@RequestParam(value="pageNum") Integer pageNum, @RequestParam(value="pageSize") Integer pageSize, UserEntity userEntity) {
        return userService.findByCondition(pageNum, pageSize, userEntity);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserEntity userEntity) {
        return userService.handleLogin(userEntity);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserEntity userEntity) {
        return userService.handleRegister(userEntity);
    }
}
