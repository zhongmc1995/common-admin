package com.zmc;

import com.zmc.common.entity.Role;
import com.zmc.constant.Constant;
import com.zmc.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */

public class RoleTest extends BaseTest {
    @Autowired
    RoleService roleService;

    @Test
    public void findAllRoles() throws Exception {
        List<Role> roles = roleService.findAllRoles();
        System.out.println(roles);
    }

    @Test
    public void deleteRoleById() throws Exception {
        Boolean result = roleService.deleteRoleById(5L);
        System.out.println(result);
    }

    @Test
    public void addRole() throws Exception {
        Role role = new Role();
        role.setName("manager");
        role.setDescribtion("总经理");
        role.setAvailable(Constant.ENABLE);
        role.setCreate_time(new Date());
        role.setCreate_by("zhongmc");
        roleService.addRole(role);
    }
}
