package com.zmc.web.bind.handler;
/**
 * Created by zhongmc on 2017/7/4.
 * 日志操作类型
 */
public enum LogType {
    SPACE(""),  
    INSERT("增加"),  
    DELETE("删除"),  
    UPDATE("修改"),  
    QUERY("查询");  
  
     private String description;  
     private LogType( String string) {  
         description=string;  
    }  
  
    public String GetDescription()  
    {  
        return description;  
    }  
} 