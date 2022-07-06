package com.anomander.noSql.task2.repository;

import com.anomander.noSql.task2.model.Task;
import com.anomander.noSql.task2.model.TaskCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findTasksByCategory(TaskCategory category);

    @Query("{'deadlineDate' : {$lt: ?0}}")
    List<Task> getOverdueTasks(LocalDateTime nowDate);

    List<Task> findByDeadlineLessThan(LocalDateTime deadline);

    List<Task> findByCategoryLike(String category);
}
