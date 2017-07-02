package com.zmc.service;

import com.zmc.common.entity.UrlFilter;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/2.
 */
public interface UrlFilterService {
    void addUrlFilter(UrlFilter urlFilter)throws Exception;
    Boolean updateUrlFilter(UrlFilter urlFilter)throws Exception;
    Boolean deleteUrlFilter(Long urlFilterId)throws Exception;

    UrlFilter findUrlFilterById(Long urlFilterId)throws Exception;
    List<UrlFilter> findAllUrlFilters()throws Exception;
}
