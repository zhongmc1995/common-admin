package com.zmc.service;

import java.util.List;

import com.zmc.common.entity.Organization;
/**
 * Created by zhongmc on 2017/6/28.
 */
public interface OrganizationService {
    void addOrganization(Organization organization)throws Exception;
    Boolean deleteOrganizationById(Long id)throws Exception;
    List<Organization> findAllOrganizations()throws Exception;
    Boolean updateOrganization(Organization organization);
}
