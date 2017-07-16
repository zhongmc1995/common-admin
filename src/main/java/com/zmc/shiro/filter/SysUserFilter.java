package com.zmc.shiro.filter;

import com.zmc.common.entity.Resource;
import com.zmc.common.vo.Menu;
import com.zmc.constant.Constant;
import com.zmc.service.ResourceService;
import com.zmc.service.UserService;
import com.zmc.spring.SpringUtils;
import com.zmc.utils.MenuHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Collection;
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
        // 全局设置左边的菜单
        List<Resource> resources = resourceService.findWildResourcesByUsername(username);
        List<Menu> menus = MenuHelper.buildMenuTree(resources);
        request.setAttribute("menus",menus);
        return true;
    }

}
