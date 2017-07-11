package com.zmc;

import com.zmc.common.entity.User;
import com.zmc.utils.EncryptHelper;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by zhongmc on 2017/6/29.
 */
public class EncryptHelperTest {
    @Test
    public void encryptTest(){
        EncryptHelper encryptHelper = new EncryptHelper();
        User user = new User();
        user.setUsername("lisi");
        user.setPassword("lisi");
        encryptHelper.encrypt(user);
        System.out.println(user);
        System.out.println(user.getPassword());
        System.out.println(user.getSalt());
    }

    @Test
    public void testUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        long l = System.currentTimeMillis();
        System.out.println(l);
    }
}
