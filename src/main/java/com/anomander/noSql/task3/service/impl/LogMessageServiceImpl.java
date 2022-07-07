package com.anomander.noSql.task3.service.impl;

import com.anomander.noSql.task3.entity.LogLevel;
import com.anomander.noSql.task3.entity.LogMessage;
import com.anomander.noSql.task3.entity.PerformanceMetrics;
import com.anomander.noSql.task3.repository.LogMessageRepository;
import com.anomander.noSql.task3.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogMessageServiceImpl implements LogMessageService {

    @Autowired
    LogMessageRepository logMessageRepository;

    @Override
    public LogMessage saveRecord(LogMessage logMessage) {
        return logMessageRepository.save(logMessage);
    }

    @Override
    public LogMessage getRecordByLogLevelAndTimestamp(LogLevel level, Long timestamp) {
        Optional<LogMessage> logMessageOptional = logMessageRepository.findByLogLevelAndTimestamp(level, timestamp);
        return logMessageOptional.orElse(new LogMessage());
    }

    @Override
    public List<LogMessage> getAllRecords() {
        return logMessageRepository.findAll();
    }

    @Override
    public void deleteRecordByLogLevelAndTimestamp(LogLevel level, Long timestamp) {
        logMessageRepository.deleteByLogLevelAndTimestamp(level, timestamp);
    }

    @Override
    public PerformanceMetrics getPerformanceMetrics(int recordsCount) {
        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.setRecordsCount(recordsCount);

        List<LogMessage> messages = generateListOfRecords(recordsCount);

        long startTime = System.currentTimeMillis();
        saveListOfRecords(messages);
        long afterAddingTime = System.currentTimeMillis();
        metrics.setAddTime(afterAddingTime-startTime);
        metrics.setAddTimePerRecord((double) metrics.getAddTime()/ recordsCount);

        findListOfRecords(messages);
        long afterSearchTime = System.currentTimeMillis();
        metrics.setSearchTime((afterSearchTime-afterAddingTime));
        metrics.setSearchTimePerRecord((double) metrics.getSearchTime()/ recordsCount);

        deleteListOfRecords(messages);
        long afterDeleteTime = System.currentTimeMillis();
        metrics.setDeleteTime(afterDeleteTime-afterSearchTime);
        metrics.setDeleteTimePerRecord((double) metrics.getDeleteTime()/ recordsCount);

        return metrics;
    }

    private void deleteListOfRecords(List<LogMessage> messages) {
        for (LogMessage m: messages) {
            logMessageRepository.deleteByLogLevelAndTimestamp(m.getLogLevel(), m.getTimestamp());
        }
    }

    private void findListOfRecords(List<LogMessage> messages) {
        for (LogMessage m: messages) {
            logMessageRepository.findByLogLevelAndTimestamp(m.getLogLevel(), m.getTimestamp());
        }
    }

    private void saveListOfRecords(List<LogMessage> messages) {
        for (LogMessage m: messages) {
            logMessageRepository.save(m);
        }
    }

    private List<LogMessage> generateListOfRecords(int recordsCount) {
        List<LogMessage> messages = new ArrayList<>();
        for (int i = 0; i < recordsCount; i++) {
            messages.add(generateRecord(i));
        }
        return messages;
    }

    private LogMessage generateRecord(int index) {
        LogLevel logLevelForIndex = LogLevel.values()[index%4];
        Long timestamp = (long) index;
        String message = "Generated message for test purposes number " + index;
        return new LogMessage(logLevelForIndex, timestamp, message);
    }
}
