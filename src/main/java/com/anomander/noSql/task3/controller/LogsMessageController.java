package com.anomander.noSql.task3.controller;

import com.anomander.noSql.task3.entity.LogLevel;
import com.anomander.noSql.task3.entity.LogMessage;
import com.anomander.noSql.task3.entity.PerformanceMetrics;
import com.anomander.noSql.task3.service.impl.LogMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsMessageController {

    @Autowired
    private LogMessageServiceImpl logMessageService;

    @PostMapping
    public ResponseEntity<LogMessage> saveRecord(@RequestBody LogMessage logMessage) {
        LogMessage savedRecord = logMessageService.saveRecord(logMessage);
        return new ResponseEntity<>(savedRecord, HttpStatus.OK);
    }

    @GetMapping(path = "{level}/{timestamp}")
    public ResponseEntity<LogMessage> getRecord(@PathVariable("level") LogLevel level, @PathVariable("timestamp")Long timestamp) {
        LogMessage logMessage = logMessageService.getRecordByLogLevelAndTimestamp(level, timestamp);
        return new ResponseEntity<>(logMessage, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LogMessage>> getRecords() {
        List<LogMessage> logMessageList = logMessageService.getAllRecords();
        return new ResponseEntity<>(logMessageList, HttpStatus.OK);
    }

    @DeleteMapping(path="{level}/{timestamp}")
    public String deleteRecord(@PathVariable("level") LogLevel level, @PathVariable("timestamp")Long timestamp) {
        logMessageService.deleteRecordByLogLevelAndTimestamp(level, timestamp);
        return "Messages deleted.";
    }

    @GetMapping(path = "performance/{recordsCount}")
    public ResponseEntity<PerformanceMetrics> runPerformanceTest(@PathVariable("recordsCount") int recordsCount) {
        PerformanceMetrics metrics = logMessageService.getPerformanceMetrics(recordsCount);
        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }
}
