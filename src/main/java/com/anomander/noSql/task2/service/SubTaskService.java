package com.anomander.noSql.task2.service;

import com.anomander.noSql.task2.model.SubTask;

import java.util.List;

public interface SubTaskService {

    List<SubTask> getAllSubtaskByTaskId(String id);

    void insertSubtasks(List<SubTask> subTasks, String id);

    void updateSubtasks(List<SubTask> subTasks, String id);
}
