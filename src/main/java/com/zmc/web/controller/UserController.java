package com.zmc.web.controller;

import com.zmc.common.entity.Response;
import com.zmc.common.entity.User;
import com.zmc.service.OrganizationService;
import com.zmc.service.RoleService;
import com.zmc.service.UserService;
import com.zmc.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by zhongmc on 2017/7/2.
 */
@Controller
@RequestMapping("/sysuser")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/sysuser-view.html",method = RequestMethod.GET)
    public String userView(Model model) throws Exception {
        //所有用户
        model.addAttribute("users",userService.findAllUsersWithFullInfo());
        //可选角色
        model.addAttribute("roles",roleService.findAllRoles());
        //可选部门
        model.addAttribute("organizations",organizationService.findAllOrganizations());
        return "user/user_view";
    }

    /**
     * 系统用户新增
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sysuser-create",method = RequestMethod.POST)
    @ResponseBody
    public Response userAdd(User user,@CurrentUser User currentUser, Model model) throws Exception {
        Response response = new Response();
        if (StringUtils.isEmpty(user.getUsername())){
            return response.failure("用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return response.failure("初始密码不能为空");
        }
        String currentUsername = currentUser.getUsername();
        user.setCreate_time(new Date());
        user.setCreate_by(currentUsername);

        userService.addUser(user);
        return response.success();
    }
}
