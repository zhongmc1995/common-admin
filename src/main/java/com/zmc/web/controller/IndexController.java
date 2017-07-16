package com.zmc.web.controller;


import com.zmc.common.entity.Organization;
import com.zmc.common.entity.User;
import com.zmc.service.OrganizationService;
import com.zmc.service.UserService;
import com.zmc.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/29.
 */
@Controller
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    /*@Log(type = LogType.QUERY,operation = "访问首页")*/
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String indexPage(@CurrentUser User currentUser, Model model) throws Exception {
        /*List<Resource> lisi = resourceService.findWildResourcesByUsername(currentUser.getUsername());
        List<Menu> menus = MenuHelper.buildMenuTree(lisi);
        model.addAttribute("menus",menus);*/
        return "index";
    }

    /*@Log(type = LogType.QUERY,operation = "查询所有用户")*/
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public @ResponseBody List<User> userList() throws Exception {
        List<User>lists = userService.findAllUsers();
        return lists;
    }

    /*@Log(type = LogType.QUERY,operation = "查询所有部门")*/
    @RequestMapping(value = "/organizations",method = RequestMethod.GET)
    public @ResponseBody List<Organization> organizationList() throws Exception {
        return organizationService.findAllOrganizations();
    }

    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
    public @ResponseBody User userWithFullInfo(@PathVariable String username) throws Exception {
        return userService.findUserByUsernameWithFullInfo(username);
    }

    /**
     * 无权访问跳转
     * @return
     */
    @RequestMapping(value = "/unauthorized.html",method = RequestMethod.GET)
    public String unauthorizedPage(){
        return "common/unauthorized";
    }


}
