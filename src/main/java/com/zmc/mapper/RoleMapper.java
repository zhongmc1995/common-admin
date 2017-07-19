package com.zmc.mapper;

import com.zmc.common.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */
public interface RoleMapper {
    /**
     * 新增角色
     */
    void insertRole(Role role) throws Exception;
    /**
     * 删除角色
     */
    Integer deleteRoleById(Long id) throws Exception;
    /**
     * 禁用角色
     */
    Integer forbidOrEnableRoleById(@Param("id") Long id, @Param("code") Integer code) throws Exception;
    /**
     * 获取所有角色
     */
    List<Role> findAllRoles()throws Exception;
    /**
     * 更新角色
     */
    Integer updateRole(Role role)throws Exception;
}
