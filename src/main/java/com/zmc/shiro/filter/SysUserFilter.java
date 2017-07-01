package com.zmc.shiro.filter;

import com.zmc.common.entity.Resource;
import com.zmc.common.vo.Menu;
import com.zmc.constant.Constant;
import com.zmc.service.ResourceService;
import com.zmc.service.UserService;
import com.zmc.utils.MenuHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/22.
 * 设置当前登陆的用户
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constant.CURRENT_USER, userService.findUserByUsername(username));

        List<Resource> resources = resourceService.findWildResourcesByUsername(username);
        List<Menu> menus = MenuHelper.buildMenuTree(resources);
        request.setAttribute("menus",menus);
        return true;
    }

}
