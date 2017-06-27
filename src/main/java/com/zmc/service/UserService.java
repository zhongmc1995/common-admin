package com.zmc.service;

import com.zmc.common.entity.User;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/27.
 */
public interface UserService {
    List<User> findAllUsers();
}
