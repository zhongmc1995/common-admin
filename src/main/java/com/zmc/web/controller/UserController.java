package com.zmc.web.controller;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Response;
import com.zmc.common.entity.User;
import com.zmc.service.OrganizationService;
import com.zmc.service.RoleService;
import com.zmc.service.UserService;
import com.zmc.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
    public Response userAdd(HttpServletRequest request, @CurrentUser User currentUser, Model model) throws Exception {
        Response response = new Response();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.size()>=5){
            if (StringUtils.isEmpty(parameterMap.get("username")[0])){
                return response.failure("用户名不能为空");
            }
            if (StringUtils.isEmpty(parameterMap.get("password")[0])){
                return response.failure("初始密码不能为空");
            }
            Long userId = System.currentTimeMillis();
            User user = new User();
            user.setId(userId);
            user.setUsername(parameterMap.get("username")[0]);
            user.setPassword(parameterMap.get("password")[0]);
            user.setCreate_time(new Date());
            user.setCreate_by(currentUser.getUsername());
            //默认禁用
            user.setLocked(1);
            if (parameterMap.get("email").length>0){
                user.setEmail(parameterMap.get("email")[0]);
            }
            if (parameterMap.get("organization").length>0){
                String organization_id = parameterMap.get("organization")[0];
                Organization o = new Organization();
                o.setId(Long.parseLong(organization_id));
                user.setOrganization(o);
            }
            userService.addUser(user);
            //当role选择一个的时候 map中的key为roles，多个的时候为roles[]
            String[] roles = null;
            if (parameterMap.containsKey("roles")){
                //选择了一个的时候
                roles = parameterMap.get("roles");
            }else {
                //选择了多个角色的时候
                roles = parameterMap.get("roles[]");
            }

            if (roles!=null&&roles.length>0){
                //用户角色关联
                for (String r : roles){
                    userService.relatedRole(userId,Long.parseLong(r));
                }
            }
            return response.success();
        }else {
            return response.failure("请求参数异常");
        }


    }

    /**
     * 系统用户删除
     *
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    @ResponseBody
    public Response userDelete(@PathVariable String id){
        Response response = new Response();
        try {
            Long userId = Long.valueOf(id);
            Boolean result = userService.deleteUserById(userId);
            userService.unRelatedRole(userId);
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
}
