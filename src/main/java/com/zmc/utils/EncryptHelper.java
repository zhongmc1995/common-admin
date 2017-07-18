package com.zmc.utils;

import com.zmc.common.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by zhongmc on 2017/6/29.
 * 加密工具类
 */
public class EncryptHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private static String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private static int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public final static void encrypt(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }

    /**
     *
     * @param user 加密好了的用户
     * @param newPwd 未加密的明文用户
     * @return
     */
    public final static Boolean doMatch(User user,String newPwd) {
        User modifyUser = new User();
        modifyUser.setUsername(user.getUsername());
        modifyUser.setSalt(user.getSalt());
        modifyUser.setPassword(newPwd);
        String modifyPassword = new SimpleHash(
                algorithmName,
                modifyUser.getPassword(),
                ByteSource.Util.bytes(modifyUser.getCredentialsSalt()),
                hashIterations).toHex();

        //user.setPassword(modifyPassword);

        return modifyPassword.equals(user.getPassword());
    }
}
