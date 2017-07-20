package com.zmc;

import com.zmc.common.entity.Organization;
import com.zmc.common.entity.Resource;
import com.zmc.common.entity.User;
import com.zmc.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by zhongmc on 2017/6/27.
 */
/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/applicationContext-service.xml"})*/
public class UserTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAllUsers() throws Exception {
        List<User> users = userService.findAllUsers();
        System.out.println(users);
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setLocked(0);
        Organization organization = new Organization();
        organization.setId(4L);
        organization.setName("测试");
        organization.setCreate_time(new Date());
        organization.setCreate_by("zhongmc");
        user.setOrganization(organization);
        user.setSalt("sfdfgdfgdfgfdgdfg");
        user.setCreate_time(new Date());
        user.setCreate_by("zhongmc");
        userService.addUser(user);
    }

    @Test
    public void relatedOrganization() throws Exception {
        User user = new User();
        user.setId(41L);
        user.setUsername("test1");
        user.setPassword("test");
        user.setLocked(0);
        Organization organization = new Organization();
        organization.setId(4L);
        organization.setName("测试");
        organization.setCreate_time(new Date());
        organization.setCreate_by("zhongmc");
        user.setSalt("sfdfgdfgdfgfdgdfg");
        user.setCreate_time(new Date());
        user.setCreate_by("zhongmc");
        Boolean b = userService.relatedOrganization(user, organization);
        System.out.println(b);
    }

    @Test
    public void findUserByUsername() throws Exception {
        User lisi = userService.findUserByUsername("lisi");
        System.out.println(lisi);
    }

    @Test
    public void modifyPassword() throws Exception {
        User user = userService.findUserByUsername("test");
        user.setId(00L);
        user.setPassword("23142142");
        user.setUpdate_time(new Date());
        user.setUpdate_by("admin");
        boolean b = userService.modifyPassword(user);
        System.out.println(b);
    }

    @Test
    public void deleteUserById() throws Exception {
        Boolean b = userService.deleteUserById(6L);
        System.out.println(b);
    }

    @Test
    public void findUserByUsernameWithFullInfo()throws Exception{
        User lisi = userService.findUserByUsernameWithFullInfo("lisi");
        System.out.println(lisi);
    }

    @Test
    public void findResourceByUsername() throws Exception {

        /*Set<Resource> lisi = userService.findResourceByUsername("lisi");
        System.out.println(lisi);*/

        String permission = "xxxx:";
        System.out.println(permission.indexOf(":"));
    }

}
