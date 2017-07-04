package com.zmc.service.Impl;


import com.zmc.common.entity.Role;

import com.zmc.constant.Constant;
import com.zmc.mapper.RoleMapper;
import com.zmc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public void addRole(Role role) throws Exception {
        roleMapper.insertRole(role);
    }

    public Boolean deleteRoleById(Long id) {
        try {
            Integer result = roleMapper.deleteRoleById(id);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Role> findAllRoles() throws Exception {
        return roleMapper.findAllRoles();
    }

    public Boolean forbidRoleById(Long id) throws Exception {
        try {
            Integer result = roleMapper.forbidOrEnableRoleById(id,Constant.DISABLE);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean enableRoleById(Long id) throws Exception {
        try {
            Integer result = roleMapper.forbidOrEnableRoleById(id,Constant.ENABLE);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }




}
