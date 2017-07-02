package com.zmc.web.controller;

import com.zmc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhongmc on 2017/7/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user-view.html",method = RequestMethod.GET)
    public String userView(Model model) throws Exception {
        model.addAttribute("users",userService.findAllUsers());
        return "user/user_view";
    }
}
