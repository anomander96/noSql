package com.anomander.noSql.task2.repository;

import com.anomander.noSql.task2.model.SubTask;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface SubTaskRepository extends MongoRepository<SubTask, String> {

//    void insertSubtasks(List<SubTask> subTasks, String id);
//
//    void updateSubtasks(List<SubTask> subTasks, String id);

//    void deleteAllSubtasks(String id);
}
