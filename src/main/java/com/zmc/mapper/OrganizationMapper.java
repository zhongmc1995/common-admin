package com.zmc.mapper;

import com.zmc.common.entity.Organization;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */
public interface OrganizationMapper {
    /**
     * 新增一个Organization
     */
    void insertOrganization(Organization organization)throws Exception;
    /**
     * 删除一个Organization
     */
    Integer deleteOrganizationById(Long id)throws Exception;
    /**
     * 获取所有的Organization
     */
    List<Organization> findAllOrganizations()throws Exception;

}
