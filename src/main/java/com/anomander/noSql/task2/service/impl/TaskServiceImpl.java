package com.anomander.noSql.task2.service.impl;

import com.anomander.noSql.task2.model.Task;
import com.anomander.noSql.task2.model.TaskCategory;
import com.anomander.noSql.task2.repository.TaskRepository;
import com.anomander.noSql.task2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void insertTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void updateTask(String id, Task task) {
        task.setId(id);
        taskRepository.save(task);
    }

    @Override
    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getOverdueTasks(LocalDateTime nowDate) {
        return taskRepository.getOverdueTasks(LocalDateTime.now());
    }

    @Override
    public List<Task> findTasksByCategory(TaskCategory category) {
        return taskRepository.findTasksByCategory(category);
    }

}
