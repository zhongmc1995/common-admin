package com.zmc.shiro.listener;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by zhongmc on 2017/6/29.
 */
public class KickOutSessionListener implements SessionListener {

    private Cache<String, LinkedList<Serializable>> cache;

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }
    public void onStart(Session session) {
        System.out.println("new session created");
    }

    public void onStop(Session session) {
        removeSessionFromCache(session);
    }

    public void onExpiration(Session session) {
        removeSessionFromCache(session);
    }

    private void removeSessionFromCache(Session session) {
        String userName = (String) session.getAttribute("username");
        if (userName != null) {
            synchronized (this.cache) {
                LinkedList<Serializable> list = this.cache.get(userName);
                list.remove(session.getId());
                System.out.println("remove the session from the cache");
                this.cache.put(userName, list);
            }
        }
    }
}