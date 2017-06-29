package com.zmc.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/22.
 */
public class MyRolesAuthorizationFilter extends RolesAuthorizationFilter {
    public MyRolesAuthorizationFilter(){}

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
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
