package com.zmc.web.controller;

import com.zmc.common.entity.Response;
import com.zmc.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhongmc on 2017/7/16.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogRecordService logRecordService;

    @RequestMapping(value = "/log-view.html",method = RequestMethod.GET)
    public String logViewPage(Model model) throws Exception {
        model.addAttribute("logs",logRecordService.findAllLogRecords());
        return "log/log_view";
    }

    /**
     * 日志删除
     */
    @RequestMapping("/{id}/delete")
    @ResponseBody
    public Response logDelete(@PathVariable String id){
        Response response = new Response();
        try {
            Long logId = Long.valueOf(id);
            Boolean result = logRecordService.deleteLogRecordById(logId);
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
}
