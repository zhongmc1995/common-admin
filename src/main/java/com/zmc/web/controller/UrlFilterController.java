package com.zmc.web.controller;

import com.zmc.service.UrlFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhongmc on 2017/7/2.
 */
@Controller
@RequestMapping(value = "/urlFilter")
public class UrlFilterController {
    @Autowired
    private UrlFilterService urlFilterService;
    @RequestMapping(value = "/urlFilter-view.html",method = RequestMethod.GET)
    public String urlFilterPage(Model model) throws Exception {
        model.addAttribute("urlFilters",urlFilterService.findAllUrlFilters());
        return "urlFilter/urlFilter_view";
    }

}
