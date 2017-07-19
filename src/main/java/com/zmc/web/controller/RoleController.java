package com.zmc.web.controller;

import com.zmc.common.entity.Response;
import com.zmc.common.entity.Role;
import com.zmc.common.entity.User;
import com.zmc.service.RoleService;
import com.zmc.web.bind.annotation.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongmc on 2017/7/19.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * role-view
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/role-view.html",method = RequestMethod.GET)
    public String roleView(Model model) throws Exception {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles",roles);
        return "role/role_view";
    }

    /**
     * role-create
     * @param role
     * @param user
     * @return
     */
    @RequestMapping(value = "/role-create",method = RequestMethod.POST)
    @ResponseBody
    public Response roleAdd(Role role, @CurrentUser User user){
        Response response = new Response();
        if (StringUtils.isEmpty(role.getName())){
            return  response.failure("角色名称不能为空");
        }
        try {
            role.setCreate_by(user.getUsername());
            role.setCreate_time(new Date());
            roleService.addRole(role);
            return response.success(role);
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("添加失败");
        }

    }

    /**
     * role-delete
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    @ResponseBody
    public Response roleDelete(@PathVariable String id){
        Response response = new Response();
        Long roleId;
        try {
            roleId = Long.valueOf(id);
        }catch (NumberFormatException e){
            e.printStackTrace();
            return response.failure("无效的ID");
        }

        try {
            Boolean result = roleService.deleteRoleById(roleId);
            if (result){
                return response.success();
            }else {
                return response.failure("失败失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("删除失败");
        }

    }

    @RequestMapping(value = "/{id}/update",method = RequestMethod.POST)
    @ResponseBody
    public Response roleUpdate(@PathVariable String id,Role role){
        Response response = new Response();
        if (StringUtils.isEmpty(role.getName())){
            return response.failure("角色名称不能为空");
        }

        try {
            role.setUpdate_time(new Date());
            role.setUpdate_by((String) SecurityUtils.getSubject().getPrincipal());
            Boolean result = roleService.updateRole(role);
            if (result){
                return response.success(role);
            }else {
                return response.failure("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("更新异常");
        }

    }
}
