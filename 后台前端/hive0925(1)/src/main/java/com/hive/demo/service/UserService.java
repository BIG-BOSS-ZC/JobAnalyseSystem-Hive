package com.hive.demo.service;

import com.hive.demo.dao.UserMapper;
import com.hive.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public List<User> findAll(){
        return userMapper.findAll();
    }
}
