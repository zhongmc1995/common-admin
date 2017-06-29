package com.zmc.web.controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.zmc.common.entity.Organization;
import com.zmc.common.entity.User;
import com.zmc.service.OrganizationService;
import com.zmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public @ResponseBody List<User> userList() throws Exception {
        List<User>lists = userService.findAllUsers();
        return lists;
    }


    @RequestMapping(value = "/organizations",method = RequestMethod.GET)
    public @ResponseBody List<Organization> organizationList() throws Exception {
        return organizationService.findAllOrganizations();
    }

    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
    public @ResponseBody User userWithFullInfo(@PathVariable String username) throws Exception {
        return userService.findUserByUsernameWithFullInfo(username);
    }

}
