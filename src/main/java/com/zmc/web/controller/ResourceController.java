package com.zmc.web.controller;

import com.zmc.common.entity.Resource;
import com.zmc.common.entity.Response;
import com.zmc.common.entity.User;
import com.zmc.common.vo.Menu;
import com.zmc.common.vo.Node;
import com.zmc.service.ResourceService;
import com.zmc.service.RoleService;
import com.zmc.utils.MenuHelper;
import com.zmc.web.bind.annotation.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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

    @RequestMapping(value = "/resource-create",method = RequestMethod.POST)
    @ResponseBody
    public Response resourceAdd(Resource resource, @CurrentUser User user){
        Response response = new Response();
        if (StringUtils.isEmpty(resource.getName())){
            return response.failure("资源名称不能为空");
        }
        String permission = resource.getPermission();
        int index = permission.indexOf(":");
        if (index < 1 || index == (permission.length()-1)){
            return response.failure("资源权限字符串不合法");
        }
        resource.setCreate_by(user.getUsername());
        resource.setCreate_time(new Date());
        try {
            resourceService.addResource(resource);
            return response.success();
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("添加失败");
        }
    }

    /**
     * resource删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    @ResponseBody
    public Response resourceDelete(@PathVariable String id, HttpServletRequest request){
        Response response = new Response();

        try {
            String child = request.getParameter("child");
            Long resourceId = Long.valueOf(id);
            List<Long> delId = new ArrayList<Long>();
            delId.add(resourceId);
            if (StringUtils.isNotEmpty(child)){
                String[] split = child.split("[/]");
                for (String s : split){
                    delId.add(Long.valueOf(s));
                }

            }
            resourceService.deleteResourceBatchByIds(delId);
            return response.success();
        }catch (NumberFormatException e){
            e.printStackTrace();
            return response.failure("无效的ID");
        } catch (Exception e) {
            e.printStackTrace();
            return response.failure("删除失败");
        }
    }
}
