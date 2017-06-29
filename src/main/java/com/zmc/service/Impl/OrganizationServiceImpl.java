package com.zmc.service.Impl;

import com.zmc.common.entity.Organization;
import com.zmc.mapper.OrganizationMapper;
import com.zmc.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;
    public void addOrganization(Organization organization) throws Exception {
        organizationMapper.insertOrganization(organization);
    }

    public Boolean deleteOrganizationById(Long id) {
        try {
            Integer result = organizationMapper.deleteOrganizationById(id);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Organization> findAllOrganizations() throws Exception {
        return organizationMapper.findAllOrganizations();
    }
}
