package com.zmc.mapper;

import com.zmc.common.entity.LogRecord;

import java.util.List;

/**
 * Created by zhongmc on 2017/7/4.
 */
public interface LogRecordMapper {
    void insertLogRecord(LogRecord logRecord)throws Exception;
    List<LogRecord> findAllLogRecords()throws Exception;
}
