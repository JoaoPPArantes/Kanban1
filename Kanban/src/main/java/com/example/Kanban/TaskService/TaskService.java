package com.example.Kanban.TaskService;


import com.example.Kanban.Entity.Enum.TaskStatus;
import com.example.Kanban.TaskRepository.TaskRepository;
import com.example.Kanban.model.TaskModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskModel insertTask(TaskModel taskModel) {
        return taskRepository.save(taskModel);
    }

    public List<TaskModel> selectAllTaskModel() {
        return taskRepository.findAll();
    }
    public TaskModel selectTaskModelById(Long id) {
        Optional<TaskModel> tk = taskRepository.findById(id);
        if(tk.isPresent()) {
            return tk.get();
        }else {
            throw new RuntimeException("Task not found");
        }
    }

    @Transactional
    public TaskModel updateTask(Long id) {
        TaskModel tk = taskRepository.getReferenceById(id);
        TaskStatus status = tk.getStatus();
        if (status == TaskStatus.Afazer){
            tk.setStatus(TaskStatus.EmProgresso);
         } else if (status == TaskStatus.EmProgresso) {
          tk.setStatus(TaskStatus.Completa);
          }
        return taskRepository.save(tk);
        }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}


