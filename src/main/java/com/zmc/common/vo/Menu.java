package com.zmc.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/29.
 * 树形菜单
 */
public class Menu implements Serializable {
    private Long id;
    private String name;
    private String type;
    private String url;
    private Long parent_id;
    private String parent_ids;
    List<Menu> child = new ArrayList<Menu>();

    public Long getId() {
        return id;
    }

    public void addChild(Menu m){
        child.add(m);
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    /**
     * 获取几级资源
     * @return
     */
    public Integer getRank(){
        String[] rank = this.parent_ids.split("[/]");
        return rank.length;
    }
}
