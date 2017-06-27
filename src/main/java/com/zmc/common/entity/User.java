package com.zmc.common.entity;

import com.zmc.common.BaseEntity;


/**
 * Created by zhongmc on 2017/6/27.
 */

/**
 * 系统用户
 */
public class User extends BaseEntity {
    /**
     * 用户名
     */
    private String username;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 加密的盐
     */
    private String salt;
    /**
     * 是否可用
     *  0 ：可用
     *  1：不可用
     */
    private Integer lock;

    /**
     * 所在的组
     */
    private Organization organization;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getLock() {
        return lock;
    }

    public void setLock(Integer lock) {
        this.lock = lock;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }


}
