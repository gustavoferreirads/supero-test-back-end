package com.supero.controller;

import java.util.List;

import com.supero.entity.Task;
import com.supero.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @GetMapping("tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        try {
            return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("task")
    public ResponseEntity<Void> addTask(@RequestBody Task task, UriComponentsBuilder builder) {
        try {
            taskService.addTask(task);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(builder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri());
            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("task")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 