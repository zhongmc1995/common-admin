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
     * 父组织ID
     */
    private Long parent_id;
    /**
     * 所有的父组织ID字符串
     */
    private String parent_ids;
    /**
     * 是否可用
     * 0：不可用
     * 1：可用
     */
    private  Integer available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(String parent_ids) {
        this.parent_ids = parent_ids;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", parent_ids='" + parent_ids + '\'' +
                ", available=" + available +
                '}';
    }
}
