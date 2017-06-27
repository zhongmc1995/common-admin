package com.zmc.common.entity;

import com.zmc.common.BaseEntity;

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
    private String desc;
    /**
     * 角色是否可用
     * 0：不可用
     * 1：可用
     */
    private Integer available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", available=" + available +
                '}';
    }
}
