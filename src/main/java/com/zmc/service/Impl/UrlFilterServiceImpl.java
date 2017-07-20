package com.zmc.service.Impl;

import com.zmc.common.entity.UrlFilter;
import com.zmc.mapper.UrlFilterMapper;
import com.zmc.service.UrlFilterService;
import com.zmc.web.bind.annotation.Log;
import com.zmc.web.bind.handler.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
/**
 * Created by zhongmc on 2017/7/2.
 */
@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    @Log(type = LogType.INSERT,operation = "新增UrlFilter")
    public void addUrlFilter(UrlFilter urlFilter)throws Exception {
        urlFilterMapper.insertUrlFilter(urlFilter);
        initFilterChain();
    }
    @Log(type = LogType.UPDATE,operation = "修改UrlFilter")
    public Boolean updateUrlFilter(UrlFilter urlFilter)throws Exception {

        try {
            Integer result  = urlFilterMapper.updateUrlFilter(urlFilter);
            initFilterChain();
            if (result>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Log(type = LogType.DELETE,operation = "删除UrlFilter")
    public Boolean deleteUrlFilter(Long urlFilterId){
        try {
            Integer result  = urlFilterMapper.deleteUrlFilter(urlFilterId);
            initFilterChain();
            if (result>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public UrlFilter findUrlFilterById(Long urlFilterId)throws Exception {
        return urlFilterMapper.findUrlFilterById(urlFilterId);
    }
    /*@Log(type = LogType.QUERY,operation = "查询所有UrlFilter")*/
    public List<UrlFilter> findAllUrlFilters()throws Exception {
        return urlFilterMapper.findAllUrlFilters();
    }

    @PostConstruct
    public void initFilterChain() throws Exception {
        shiroFilerChainManager.initFilterChains(findAllUrlFilters());
    }

}
