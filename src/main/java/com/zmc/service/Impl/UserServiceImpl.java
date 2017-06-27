package com.zmc.service.Impl;

import com.zmc.common.entity.User;
import com.zmc.mapper.UserMapper;
import com.zmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }
}
