package com.example.Kanban.controller;

import com.example.Kanban.TaskService.TaskService;
import com.example.Kanban.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskControlller {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public TaskModel criarTask(@RequestBody TaskModel taskModel) {
        return taskService.insertTask(taskModel);
    }

    @GetMapping
    public List<TaskModel> listTasks() {
        return taskService.selectAllTaskModel();
    }

    @GetMapping("/{id}")
    public TaskModel getTask(@PathVariable Long id) {
        return taskService.selectTaskModelById(id);
    }

    @PutMapping("/{id}/atualizar")
    public TaskModel atualizarTask(@PathVariable Long id) {
        return taskService.selectTaskModelById(id);
    }

    @DeleteMapping("/{id}")
    public void deletarTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
