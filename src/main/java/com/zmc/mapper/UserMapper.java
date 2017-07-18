package com.zmc.mapper;

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
public interface UserMapper {
    /**
     * 获取所有的用户
     * @return
     */
    List<User> findAllUsers() throws Exception;
    /**
     * 获取所有用户（用户详情信息）
     */
    List<User> findAllUsersWithFullInfo()throws Exception;

    User findUserById(Long id)throws Exception;

    /**
     * 新增一个用户
     * @param user
     * @throws Exception
     */
    void insertUser(User user)throws Exception;

    Integer updateUser(User user)throws Exception;

    Integer relatedOrganization(@Param("u") User user, @Param("o") Organization organization) throws Exception;

    List<User> findUserByUsername(String username)throws Exception;

    Integer modifyPassword(User user)throws Exception;

    Integer deleteUserById(Long id)throws Exception;

    User findUserByUsernameWithFullInfo(String username)throws Exception;

    Set<Role> findRolesByUsername(String username)throws Exception;

    Set<Resource> findResourceByUsername(String username);

    void relatedRole(@Param("user_id") Long user_id,@Param("role_id") Long role_id)throws Exception;

    Integer unRelatedRole(Long id) throws Exception;
}
