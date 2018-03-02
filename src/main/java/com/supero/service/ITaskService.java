package com.supero.service;

import java.util.List;

import com.supero.entity.Task;

public interface ITaskService {
    List<Task> getAllTasks();

    void addTask(Task task);

    void updateTask(Task task);

    void deleteTask(Long taskId);
}
