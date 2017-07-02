package com.zmc.mapper;

import com.zmc.common.entity.UrlFilter;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/2.
 */
public interface UrlFilterMapper {
    UrlFilter insertUrlFilter(UrlFilter urlFilter);
    Integer updateUrlFilter(UrlFilter urlFilter);
    Integer deleteUrlFilter(Long id);

    UrlFilter findUrlFilterById(Long urlFilterId);
    List<UrlFilter> findAllUrlFilters();
}
