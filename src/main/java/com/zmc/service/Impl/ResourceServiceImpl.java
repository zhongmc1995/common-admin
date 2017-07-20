package com.zmc.service.Impl;

import com.zmc.common.entity.Resource;
import com.zmc.mapper.ResourceMapper;
import com.zmc.mapper.UserMapper;
import com.zmc.service.ResourceService;
import com.zmc.web.bind.annotation.Log;
import com.zmc.web.bind.handler.LogType;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/29.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserMapper userMapper;
    public void addResource(Resource resource) throws Exception {
        resourceMapper.insertResource(resource);
    }

    @Log(type = LogType.QUERY,operation = "查询所有资源")
    public List<Resource> findAllResources() throws Exception {
        return resourceMapper.findAllResources();
    }

    public Boolean deleteResourceById(Long id) {
        try {
            Integer result = resourceMapper.deleteResourceById(id);
            return result > 0 ? true : false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(value="menu-resource",key="#p0")
    public List<Resource> findWildResourcesByUsername(String username) throws Exception {
        List<Resource> wildResourcesForUser = new ArrayList<Resource>();
        Set<Resource> resourcesForUser = userMapper.findResourceByUsername(username);
        List<Resource> allResources = resourceMapper.findAllResources();
        Set<String> permissionForUser = new HashSet<String>();
        for (Resource r : resourcesForUser){
            permissionForUser.add(r.getPermission());
        }
        for (Resource r : allResources){
            if (hasPermission(permissionForUser,r)){
                wildResourcesForUser.add(r);
            }
        }

        return wildResourcesForUser;
    }

    public List<Resource> findResourceByRoleId(Long id) throws Exception {
        return resourceMapper.findResourceByRoleId(id);
    }

    /**
     * 判断Resource是否在permission的操控范围内
     * @param permissions
     * @param resource
     * @return
     */
    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        WildcardPermission p2 = new WildcardPermission(resource.getPermission());
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
