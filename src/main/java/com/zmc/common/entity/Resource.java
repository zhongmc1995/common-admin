package com.zmc.common.entity;

import com.zmc.common.BaseEntity;

/**
 * Created by zhongmc on 2017/6/27.
 */
public class Resource extends BaseEntity {
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型
     * 比如：菜单，按钮
     */
    private String type;
    /**
     * 资源url
     */
    private String url;
    /**
     * 资源的父ID
     */
    private Long parent_id;
    /**
     * 所有父ID字符串
     */
    private String parent_ids;
    /**
     * 资源的权限描述
     * user:create..
     */
    private String permission;
    /**
     * 是否可用
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", parent_id=" + parent_id +
                ", parent_ids='" + parent_ids + '\'' +
                ", permission='" + permission + '\'' +
                ", available=" + available +
                '}';
    }
}
