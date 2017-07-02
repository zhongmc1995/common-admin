package com.zmc.service.Impl;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Role;
import com.zmc.common.entity.User;
import com.zmc.mapper.UserMapper;
import com.zmc.service.UserService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/27.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUsers() throws Exception {
        return userMapper.findAllUsers();
    }

    public List<User> findAllUsersWithFullInfo() throws Exception {
        return userMapper.findAllUsersWithFullInfo();
    }

    public void addUser(User user) throws Exception {
        userMapper.insertUser(user);
    }

    public Boolean relatedOrganization(User user, Organization organization) throws Exception {
        try {
            Integer result  = userMapper.relatedOrganization(user,organization);
            if (result>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByUsername(String username) throws Exception {
        List<User> users = userMapper.findUserByUsername(username);
        return (users==null || users.size()==0) ? null : users.get(0);
    }

    public Boolean modifyPassword(User user) {
        try {
            Integer result  = userMapper.modifyPassword(user);
            if (result>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean deleteUserById(Long id) throws Exception {
        try {
            Integer result  = userMapper.deleteUserById(id);
            if (result>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findUserByUsernameWithFullInfo(String username) throws Exception {
        return userMapper.findUserByUsernameWithFullInfo(username);
    }

    public Set<Role> findRolesByUsername(String usernmae) throws Exception {
        return userMapper.findRolesByUsername(usernmae);
    }

    /**
     * 根据用户名查找对应的资源
     * @param username
     * @return
     */
    public Set<Resource> findResourceByUsername(String username)throws Exception {
        return userMapper.findResourceByUsername(username);
    }
}
