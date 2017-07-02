package com.zmc.shiro.filter;

import com.zmc.common.entity.Resource;
import com.zmc.common.vo.Menu;
import com.zmc.service.ResourceService;
import com.zmc.utils.MenuHelper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/22.
 */
public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {
    public MyRolesAuthorizationFilter(){}
    @Autowired
    private ResourceService resourceService;
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = this.getSubject(request, response);
        // 全局设置左边的菜单
        List<Resource> resources = null;
        try {
            resources = resourceService.findWildResourcesByUsername((String) subject.getPrincipal());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Menu> menus = MenuHelper.buildMenuTree(resources);
        request.setAttribute("menus",menus);
        String[] rolesArray = (String[])((String[])mappedValue);
        if(rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            for (String role : roles){
                if (subject.hasRole(role)){
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
