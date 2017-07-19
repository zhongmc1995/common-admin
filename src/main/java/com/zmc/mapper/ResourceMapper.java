package com.zmc.mapper;

import com.zmc.common.entity.Resource;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/29.
 */
public interface ResourceMapper {
    void insertResource(Resource resource)throws Exception;
    List<Resource> findAllResources()throws Exception;
    Integer deleteResourceById(Long id)throws Exception;
    List<Resource> findResourceByRoleId(Long id)throws Exception;
}
