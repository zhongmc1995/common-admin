package com.zmc.service.Impl;

import com.zmc.common.entity.Organization;
import com.zmc.mapper.LogRecordMapper;
import com.zmc.mapper.OrganizationMapper;
import com.zmc.service.OrganizationService;
import com.zmc.web.bind.annotation.Log;
import com.zmc.web.bind.handler.LogType;
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
    @Log(type = LogType.INSERT,operation = "新增部门")
    public void addOrganization(Organization organization) throws Exception {
        organizationMapper.insertOrganization(organization);
    }
    @Log(type = LogType.DELETE,operation = "删除部门")
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
    @Log(type = LogType.QUERY,operation = "查询所有部门")
    public List<Organization> findAllOrganizations() throws Exception {
        return organizationMapper.findAllOrganizations();
    }

    /**
     * 更新一个部门
     * @param organization
     * @return
     */
    @Log(type = LogType.UPDATE,operation = "更新部门")
    public Boolean updateOrganization(Organization organization) {

        try {
            Integer result = organizationMapper.updateOrganization(organization);
            if (result > 0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


}
