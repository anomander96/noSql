package com.anomander.noSql.task2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;

    private String name;

    private LocalDateTime dateOfCreation;

    private LocalDateTime deadline;

    private String description;

    private TaskCategory category;

    @DBRef
    List<SubTask> subTasks;

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
