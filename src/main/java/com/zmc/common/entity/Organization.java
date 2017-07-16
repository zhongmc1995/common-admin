package com.zmc.common.entity;

import com.zmc.common.BaseEntity;

/**
 * Created by zhongmc on 2017/6/27.
 */
public class Organization extends BaseEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 是否可用
     * 0：不可用
     * 1：可用
     */
    private  Integer available;

    /**
     * 描述
     */
    private String describe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
