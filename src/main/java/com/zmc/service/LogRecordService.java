package com.zmc.service;

import com.zmc.common.entity.LogRecord;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/4.
 */
public interface LogRecordService {
    void addLogRecord(LogRecord logRecord)throws Exception;
    List<LogRecord> findAllLogRecords()throws Exception;

    Boolean deleteLogRecordById(Long id)throws Exception;
}
