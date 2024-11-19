package com.example.Kanban.TaskRepository;

import com.example.Kanban.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Kanban.model.TaskModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
}
