package com.zmc.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zmc.common.BaseEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by zhongmc on 2017/6/27.
 */

/**
 * 系统用户
 */
public class User extends BaseEntity implements Serializable {
    public User(){}

    public User(String username, String password, String email, String phone, String salt, Integer locked,
                Organization organization, Date create_time, Date update_time, String create_by, String update_by) {
        super(create_time,update_time,create_by,update_by);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.salt = salt;
        this.locked = locked;
        this.organization = organization;
    }

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
    private Integer locked;

    /**
     * 所在的组
     */
    private Organization organization;

    /**
     * 所有角色
     * @return
     */

    private List<Role> roles;
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

    public String getCredentialsSalt() {
        return username + salt;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
