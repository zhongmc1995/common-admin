package com.zmc.service;

import com.zmc.common.entity.Role;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */
public interface RoleService {
    void addRole(Role role) throws Exception;
    Boolean deleteRoleById(Long id)throws Exception;
    List<Role> findAllRoles()throws Exception;
    Boolean forbidRoleById(Long id) throws Exception;
    Boolean enableRoleById(Long id) throws Exception;
}
