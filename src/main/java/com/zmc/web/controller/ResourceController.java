package com.zmc.web.controller;

import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Response;
import com.zmc.common.vo.Menu;
import com.zmc.common.vo.Node;
import com.zmc.service.ResourceService;
import com.zmc.service.RoleService;
import com.zmc.utils.MenuHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongmc on 2017/7/19.
 */
@Controller
@RequestMapping("resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;

    /**
     * ajax获取所有的Menu
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllMenu",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Resource> getAllMenu() throws Exception {
        List<Resource> resources = resourceService.findAllResources();
        List<Menu> menus = MenuHelper.buildMenuTree(resources);
        return resources;
    }

    @RequestMapping(value = "/getMenuForRole",method = RequestMethod.GET)
    @ResponseBody
    public List<Resource> getMenuForRole(String id)throws Exception{
        Long roleId ;
        try {
            roleId = Long.valueOf(id);
            //resourceService
            List<Resource> resourceForRole = resourceService.findResourceByRoleId(roleId);
            return resourceForRole;
        }catch (NumberFormatException e){
            return null;
        }

    }

    @RequestMapping(value = "/grant/{roleId}",method = RequestMethod.POST)
    @ResponseBody
    public Response grantResourceForRole(@PathVariable String roleId, @RequestBody ArrayList<Node> nodes){
        Response response = new Response();
        try {
            Long id = Long.valueOf(roleId);
            for (Node node : nodes){
                if (node.getChecked()){
                    // 被选中
                    roleService.relateRoleAndResource(id,node.getId());
                }else {
                    // 取消关联
                    roleService.unelateRoleAndResource(id,node.getId());
                }
            }
            return response.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return response.failure("无效的角色");
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("授权异常");
        }

    }

    @RequestMapping(value = "/resource-view.html",method = RequestMethod.GET)
    public String resourceView(Model model) throws Exception {

        List<Resource> resources = resourceService.findAllResources();
        model.addAttribute("resources",resources);
        return "resource/resource_view";
    }
}
