package com.zmc.service.Impl;

import com.zmc.common.entity.LogRecord;
import com.zmc.mapper.LogRecordMapper;
import com.zmc.service.LogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/4.
 */
@Service
public class LogRecordServiceImpl implements LogRecordService {
    @Autowired
    private LogRecordMapper logRecordMapper;
    public void addLogRecord(LogRecord logRecord) throws Exception {
        logRecordMapper.insertLogRecord(logRecord);
    }

    public List<LogRecord> findAllLogRecords() throws Exception {
        return logRecordMapper.findAllLogRecords();
    }

    public Boolean deleteLogRecordById(Long id) throws Exception {
        try {
            Integer result = logRecordMapper.deleteLogRecord(id);
            if (result>0)
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
