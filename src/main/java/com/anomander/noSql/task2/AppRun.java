package com.anomander.noSql.task2;

import com.anomander.noSql.task2.model.SubTask;
import com.anomander.noSql.task2.model.Task;
import com.anomander.noSql.task2.model.TaskCategory;
import com.anomander.noSql.task2.service.impl.SubTaskServiceImpl;
import com.anomander.noSql.task2.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppRun implements CommandLineRunner {

    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    SubTaskServiceImpl subTaskService;

    @Override
    public void run(String... args) throws Exception {
        List<Task> tasks = taskService.getAllTasks();
        System.out.println(tasks.toString());

        System.out.println(taskService.getOverdueTasks(LocalDateTime.now()));

        System.out.println(taskService.findTasksByCategory(TaskCategory.DEVELOP));

        Task task = new Task("2", "a second task");

        taskService.insertTask(task);
        System.out.println(taskService.getAllTasks());

        Task updatedTask = new Task("2", "this is an updated task");
        taskService.updateTask(task.getId(), updatedTask);

        taskService.delete(task.getId()); // delete task with id 2

        List<SubTask> subTasks = subTaskService.getAllSubtaskByTaskId(task.getId());

        subTaskService.insertSubtasks(subTasks, task.getId());

        subTaskService.updateSubtasks(subTasks, updatedTask.getId());

    }

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }

}
