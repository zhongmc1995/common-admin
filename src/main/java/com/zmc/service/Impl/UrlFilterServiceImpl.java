package com.zmc.service.Impl;

import com.zmc.common.entity.UrlFilter;
import com.zmc.mapper.UrlFilterMapper;
import com.zmc.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UrlFilterServiceImpl implements UrlFilterService {

    @Autowired
    private UrlFilterMapper urlFilterMapper;

    @Autowired
    private ShiroFilerChainManager shiroFilerChainManager;

    public void addUrlFilter(UrlFilter urlFilter)throws Exception {
        urlFilterMapper.insertUrlFilter(urlFilter);
        initFilterChain();
    }

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


    public Boolean deleteUrlFilter(Long urlFilterId)throws Exception {
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

    public List<UrlFilter> findAllUrlFilters()throws Exception {
        return urlFilterMapper.findAllUrlFilters();
    }

    @PostConstruct
    public void initFilterChain() throws Exception {
        shiroFilerChainManager.initFilterChains(findAllUrlFilters());
    }

}
