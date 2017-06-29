package com.zmc.service;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Role;
import com.zmc.common.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/27.
 */
public interface UserService {
    List<User> findAllUsers() throws Exception;
    void addUser(User user)throws Exception;
    Boolean relatedOrganization(User user, Organization organization) throws Exception;
    User findUserByUsername(String username)throws Exception;
    Boolean modifyPassword(User user)throws Exception;
    Boolean deleteUserById(Long id)throws Exception;
    User findUserByUsernameWithFullInfo(String username)throws Exception;
    Set<Role> findRolesByUsername(String usernmae)throws Exception;

    /**
     * 查询该用户具有的权限字符串
     * @param username
     * @return
     */
    Set<Resource> findResourceByUsername(String username)throws Exception;
}
