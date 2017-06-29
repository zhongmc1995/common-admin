package com.zmc.service.Impl;

import com.zmc.common.entity.Resource;
import com.zmc.mapper.ResourceMapper;
import com.zmc.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/29.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    public void addResource(Resource resource) throws Exception {
        resourceMapper.insertResource(resource);
    }

    public List<Resource> findAllResouces() throws Exception {
        return resourceMapper.findAllResources();
    }

    public Boolean deleteResourceById(Long id) {
        try {
            Integer result = resourceMapper.deleteResourceById(id);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
