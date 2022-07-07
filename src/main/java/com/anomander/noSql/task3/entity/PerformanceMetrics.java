package com.anomander.noSql.task3.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceMetrics {
    private int recordsCount;
    private long addTime;
    private double addTimePerRecord;
    private long searchTime;
    private double searchTimePerRecord;
    private long deleteTime;
    private double deleteTimePerRecord;
}
