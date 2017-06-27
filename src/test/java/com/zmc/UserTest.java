package com.zmc;

import com.zmc.common.entity.User;
import com.zmc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/27.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/spring/applicationContext-service.xml"})
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAllUsers(){
        List<User> users = userService.findAllUsers();
        System.out.println(users);
    }
}
