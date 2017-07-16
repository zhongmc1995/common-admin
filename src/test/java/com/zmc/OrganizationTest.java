package com.zmc;

import com.zmc.common.entity.Organization;
import com.zmc.service.OrganizationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/28.
 */

public class OrganizationTest extends BaseTest {
    @Autowired
    OrganizationService organizationService;

    @Test
    public void findAllOrganizations() throws Exception {
        List<Organization> or = organizationService.findAllOrganizations();
        System.out.println(or);
    }

    @Test
    public void deleteOrganizationById() throws Exception {
        Boolean result = organizationService.deleteOrganizationById(4L);
        System.out.println(result);
    }

    @Test
    public void addOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setName("产品研发部");
        organization.setAvailable(1);
        organization.setCreate_time(new Date());
        organization.setCreate_by("admin");
        organizationService.addOrganization(organization);
    }
}
