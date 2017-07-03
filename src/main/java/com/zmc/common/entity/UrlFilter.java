package com.zmc.common.entity;

import com.zmc.common.BaseEntity;

/**
 * Created by zhongmc on 2017/7/2.
 */
public class UrlFilter extends BaseEntity {
    private String name; //url名称/描述
    private String url; //地址
    private String roles; //所需要的角色，可省略
    private String permissions; //所需要的权限，可省略

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlFilter urlFilter = (UrlFilter) o;

        if (getId() != null ? !getId().equals(urlFilter.getId()) : urlFilter.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UrlFilter{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", roles='" + roles + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }
}
