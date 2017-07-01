package com.zmc.web.controller;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.User;
import com.zmc.common.vo.Menu;
import com.zmc.service.OrganizationService;
import com.zmc.service.ResourceService;
import com.zmc.service.UserService;
import com.zmc.utils.MenuHelper;
import com.zmc.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/1.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/organization-view.html",method = RequestMethod.GET)
    public String organizationView(@CurrentUser User currentUser,Model model) throws Exception {
        List<Organization> organizations = organizationService.findAllOrganizations();
        model.addAttribute("organizations",organizations);
        /*List<Resource> lisi = resourceService.findWildResourcesByUsername(currentUser.getUsername());
        List<Menu> menus = MenuHelper.buildMenuTree(lisi);
        model.addAttribute("menus",menus);*/

        return "organization/organization_view";
    }


}
