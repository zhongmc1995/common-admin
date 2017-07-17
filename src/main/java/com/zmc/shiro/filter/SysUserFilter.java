package com.zmc.shiro.filter;

import com.zmc.common.entity.Resource;
import com.zmc.common.entity.User;
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
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
        //设置缓存
        buildCurrentUser(username,request);
        // 全局设置左边的菜单 findWildResourcesByUsername设置了缓存
        List<Resource> resources = resourceService.findWildResourcesByUsername(username);
        List<Menu> menus = MenuHelper.buildMenuTree(resources);
        request.setAttribute("menus",menus);
        return true;
    }

    /**
     * 设置当前登陆用户
     * 带有缓存功能
     * @param username
     * @param request
     * @throws Exception
     */
    private void buildCurrentUser(String username,ServletRequest request) throws Exception {
        CacheManager cacheManager = SpringUtils.getBean("springCacheManager");
        Cache currentUser = cacheManager.getCache("currentUser");
        Cache.ValueWrapper valueWrapper = currentUser.get(username);
        User loginUser;
        if (valueWrapper==null){
            currentUser.put(username,userService.findUserByUsername(username));
            loginUser = userService.findUserByUsername(username);
        }else {
            loginUser = (User) valueWrapper.get();
        }
        request.setAttribute(Constant.CURRENT_USER, loginUser);
    }

}
