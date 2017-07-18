package com.zmc.service;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Role;
import com.zmc.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/27.
 */
public interface UserService {
    List<User> findAllUsers() throws Exception;
    User findUserById(Long id) throws Exception;
    List<User> findAllUsersWithFullInfo()throws Exception;
    void addUser(User user)throws Exception;
    Boolean relatedOrganization(User user, Organization organization) throws Exception;
    User findUserByUsername(String username)throws Exception;
    Boolean modifyPassword(User user)throws Exception;
    Boolean deleteUserById(Long id)throws Exception;
    User findUserByUsernameWithFullInfo(String username)throws Exception;
    Set<Role> findRolesByUsername(String usernmae)throws Exception;
    Boolean updateUser(User user)throws Exception;

    /**
     * 查询该用户具有的权限字符串
     * @param username
     * @return
     */
    Set<Resource> findResourceByUsername(String username)throws Exception;

    /**
     * 用户关联角色
     */
    void relatedRole(Long user_id,Long role_id)throws Exception;

    /**
     * 角色用户取消关联
     * @param id
     * @return
     * @throws Exception
     */
    Boolean unRelatedRole(Long id) throws Exception;

}
