package com.zmc.common.entity;

import com.zmc.common.BaseEntity;

import java.util.List;

/**
 * Created by zhongmc on 2017/6/27.
 */
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String describtion;
    /**
     * 角色是否可用
     * 0：不可用
     * 1：可用
     */
    private Integer available;

    /**
     * 拥有该角色的所有用户
     * @return
     */
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", describtion='" + describtion + '\'' +
                ", available=" + available +
                '}';
    }
}
