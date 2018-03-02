package com.supero.repository;

import com.supero.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
public interface ITaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrderByCreateAtDesc();
}
