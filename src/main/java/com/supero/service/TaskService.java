package com.supero.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.supero.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supero.entity.Task;

@Service
public class TaskService implements ITaskService {
    private ZoneId zoneId = ZoneId.of("America/Sao_Paulo");

    @Autowired
    private ITaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreateAtDesc();
    }

    @Override
    public void addTask(Task task) { //Conversion required to avoid serialization problems
        LocalDateTime ldt = LocalDateTime.ofInstant(new Date().toInstant(), zoneId);
        task.setUpdateAt(Date.from(ldt.atZone(zoneId).toInstant()));
        task.setCreateAt(Date.from(ldt.atZone(zoneId).toInstant()));
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        LocalDateTime ldt = LocalDateTime.ofInstant(new Date().toInstant(), zoneId);
        task.setUpdateAt(Date.from(ldt.atZone(zoneId).toInstant()));
        if (task.getIsComplete()) {
            task.setCompleteAt(Date.from(ldt.atZone(zoneId).toInstant()));
        }
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.delete(taskId);
    }
}
