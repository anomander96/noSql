package com.anomander.noSql.task3.service;

import com.anomander.noSql.task3.entity.LogLevel;
import com.anomander.noSql.task3.entity.LogMessage;
import com.anomander.noSql.task3.entity.PerformanceMetrics;

import java.util.List;

public interface LogMessageService {

    LogMessage saveRecord(LogMessage logMessage);

    LogMessage getRecordByLogLevelAndTimestamp(LogLevel level, Long timestamp);

    List<LogMessage> getAllRecords();

    void deleteRecordByLogLevelAndTimestamp(LogLevel level, Long timestamp);

    PerformanceMetrics getPerformanceMetrics(int recordsCount);
}
