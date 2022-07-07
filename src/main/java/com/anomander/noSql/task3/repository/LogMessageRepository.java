package com.anomander.noSql.task3.repository;

import com.anomander.noSql.task3.entity.LogLevel;
import com.anomander.noSql.task3.entity.LogMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface LogMessageRepository extends CrudRepository<LogMessage, Serializable> {

    Optional<LogMessage> findByLogLevelAndTimestamp(LogLevel level, Long timestamp);
    List<LogMessage> findAll();
    void deleteByLogLevelAndTimestamp(LogLevel level, Long timestamp);
}
