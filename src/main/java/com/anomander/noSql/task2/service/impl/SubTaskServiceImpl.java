package com.anomander.noSql.task2.service.impl;

import com.anomander.noSql.task2.repository.SubTaskRepository;
import com.anomander.noSql.task2.service.SubTaskService;
import com.anomander.noSql.task2.model.SubTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskServiceImpl implements SubTaskService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SubTaskRepository subTaskRepository;

    @Override
    public List<SubTask> getAllSubtaskByTaskId(String id) {
        return subTaskRepository.findAll();
    }

    @Override
    public void insertSubtasks(List<SubTask> subTasks, String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        List<SubTask> existSubtasks = mongoTemplate.find(query, SubTask.class);
        existSubtasks.addAll(subTasks);

        Update update = new Update();
        update.set("subtask", subTasks);

        mongoTemplate.updateFirst(query, update, SubTask.class);
    }

    @Override
    public void updateSubtasks(List<SubTask> subTasks, String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("subtask", subTasks);

        mongoTemplate.updateFirst(query, update, SubTask.class);
    }
}
