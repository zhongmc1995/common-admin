package com.zmc.service.Impl;

import com.zmc.common.entity.UrlFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongmc on 2017/7/2.
 */
@Service
public class ShiroFilerChainManager {

    @Autowired
    private DefaultFilterChainManager filterChainManager;

    private Map<String, NamedFilterList> defaultFilterChains = new LinkedHashMap<String, NamedFilterList>();

    @PostConstruct
    public void init() {
        Map<String, NamedFilterList> oldFilterChains = filterChainManager.getFilterChains();
        for (Map.Entry<String,NamedFilterList> e : oldFilterChains.entrySet()){
            defaultFilterChains.put(e.getKey(),e.getValue());
        }
    }

    public void initFilterChains(List<UrlFilter> urlFilters) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        Map.Entry<String, NamedFilterList> last = null;
        if(defaultFilterChains != null) {
            //filterChainManager.getFilterChains().putAll(defaultFilterChains);
            /*for (Map.Entry<String,NamedFilterList> e : defaultFilterChains.entrySet()){
                filterChainManager.getFilterChains().put(e.getKey(),e.getValue());
            }*/
            Iterator<Map.Entry<String, NamedFilterList>> iterator = defaultFilterChains.entrySet().iterator();
            int len = defaultFilterChains.size();
            int count = 0;
            while (iterator.hasNext()){
                if ((count+1)>=len){
                    last = iterator.next();
                    break;
                }
                Map.Entry<String, NamedFilterList> next = iterator.next();
                filterChainManager.getFilterChains().put(next.getKey(),next.getValue());
                count++;
            }
        }

        //2、循环URL Filter 注册filter chain
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
                filterChainManager.addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
                filterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }
        }
        if (null!=last){
            filterChainManager.getFilterChains().put(last.getKey(),last.getValue());
        }
    }
}
