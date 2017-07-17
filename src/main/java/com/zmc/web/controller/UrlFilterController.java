package com.zmc.web.controller;

import com.zmc.common.entity.Response;
import com.zmc.common.entity.UrlFilter;
import com.zmc.common.entity.User;
import com.zmc.service.UrlFilterService;
import com.zmc.web.bind.annotation.CurrentUser;
import com.zmc.web.bind.annotation.Log;
import com.zmc.web.bind.handler.LogType;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.Area;
import java.util.Date;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by zhongmc on 2017/7/2.
 */
@Controller
@RequestMapping(value = "/urlFilter")
public class UrlFilterController {
    @Autowired
    private UrlFilterService urlFilterService;
    @Log(type = LogType.QUERY,operation = "查询UrlFiltr")
    @RequestMapping(value = "/urlFilter-view.html",method = RequestMethod.GET)
    public String urlFilterPage(Model model) throws Exception {
        model.addAttribute("urlFilters",urlFilterService.findAllUrlFilters());
        return "urlFilter/urlFilter_view";
    }

    /**
     * urlFilter新增
     */
    @RequestMapping(value = "/urlFilter-create")
    @ResponseBody
    public Response addUrlFilter(@CurrentUser User user, UrlFilter urlFilter) {
        Response response = new Response();
        if (StringUtils.isEmpty(urlFilter.getName())){
            return response.failure("名称不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getUrl())){
            return response.failure("url不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getRoles())){
            return response.failure("角色不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getPermissions())){
            return response.failure("权限不能为空");
        }
        String currentUserName = user.getUsername();
        urlFilter.setCreate_time(new Date());
        urlFilter.setCreate_by(currentUserName);
        try {
            urlFilterService.addUrlFilter(urlFilter);
            return new Response().success(urlFilter);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("添加失败");
        }

    }

    /**
     * urlFilter删除
     *
     */
    @RequestMapping(value = "/{idStr}/delete",method = RequestMethod.GET)
    @ResponseBody
    public Response deleteUrlFilter(@PathVariable String idStr){
        Response response = new Response();
        try {
            Long id = Long.valueOf(idStr);
            Boolean result = urlFilterService.deleteUrlFilter(id);
            if (result){
                return response.success();
            }else {
                return response.failure("删除失败");
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
            return response.failure("无效的ID");
        }catch (Exception e) {
            e.printStackTrace();
            return response.failure("删除失败");
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/{idStr}/update")
    @ResponseBody
    public Response updateUrlFilter(@PathVariable String idStr,UrlFilter urlFilter){
        Response response = new Response();
        if (StringUtils.isEmpty(urlFilter.getName())){
            return response.failure("名称不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getUrl())){
            return response.failure("url不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getRoles())){
            return response.failure("角色不能为空");
        }
        if (StringUtils.isEmpty(urlFilter.getPermissions())){
            return response.failure("权限不能为空");
        }
        String currentUserName = (String) SecurityUtils.getSubject().getPrincipal();
        urlFilter.setUpdate_by(currentUserName);
        urlFilter.setUpdate_time(new Date());
        try {
            Boolean result = urlFilterService.updateUrlFilter(urlFilter);
            if (result){
                return response.success();
            }else {
                return response.failure("更新失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Response().failure("添加失败");
        }
    }
}
