package com.zmc;

import com.zmc.common.entity.Resource;
import com.zmc.service.ResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/29.
 */
public class ResourceTest extends BaseTest {
    @Autowired
    private ResourceService resourceService;

    @Test
    public void findAllResources() throws Exception {
        List<Resource> resources = resourceService.findAllResouces();
        System.out.println(resources);
    }

    @Test
    public void addResource() throws Exception {
        Resource resource = new Resource();
        resource.setName("测试");
        resource.setType("菜单");
        resource.setAvailable(1);
        resource.setParent_id(11L);
        resource.setParent_ids("0/1/11/");
        resource.setUrl("");
        resource.setPermission("organization:test");
        resource.setCreate_time(new Date());
        resource.setCreate_by("admin");
        resourceService.addResource(resource);
    }

    @Test
    public void deleteResourceById() {
        Boolean result = resourceService.deleteResourceById(51L);
        System.out.println(result);
    }
}
