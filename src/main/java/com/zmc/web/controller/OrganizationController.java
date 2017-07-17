package com.zmc.web.controller;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Response;
import com.zmc.common.entity.User;
import com.zmc.service.OrganizationService;
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
 * Created by zhongmc on 2017/7/1.
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/organization-view.html",method = RequestMethod.GET)
    public String organizationView(Model model) throws Exception {
        List<Organization> organizations = organizationService.findAllOrganizations();
        model.addAttribute("organizations",organizations);
        return "organization/organization_view";
    }

    @RequestMapping(value = "/organization-create",method = RequestMethod.POST)
    @ResponseBody
    public Response organizationAdd(Organization organization,@CurrentUser User user){
        Response response = new Response();
        if (StringUtils.isEmpty(organization.getName())){
            return response.failure("名称不能为空");
        }
        String username = user.getUsername();
        organization.setCreate_by(username);
        organization.setCreate_time(new Date());
        try {
            organizationService.addOrganization(organization);
            return new Response().success(organization);
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("添加失败");
        }
    }


    /**
     * 部门删除
     *
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    @ResponseBody
    public Response organizationDelete(@PathVariable String id){
        Response response = new Response();
        try {
            Long organizationId = Long.valueOf(id);
            Boolean result = organizationService.deleteOrganizationById(organizationId);
            if (result){
                return response.success();
            }else {
                return response.failure("删除失败");
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            return response.failure("无效的ID");
        }catch (Exception e) {
            e.printStackTrace();
            return response.failure("删除失败");
        }
    }


    @RequestMapping(value = "/{id}/update",method = RequestMethod.GET)
    @ResponseBody
    public Response organizationUpdate(@PathVariable String id,Organization organization){
        Response response = new Response();
        if (StringUtils.isEmpty(organization.getName())){
            return response.failure("部门名称不能为空");
        }
        if (StringUtils.isEmpty(organization.getAvailable())){
            return response.failure("请开启是否可用");
        }

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        organization.setUpdate_time(new Date());
        organization.setCreate_by(username);

        Boolean result = organizationService.updateOrganization(organization);
        if (result){
            return response.success(organization);
        }else {
            return response.failure("更新失败");
        }
    }
}
