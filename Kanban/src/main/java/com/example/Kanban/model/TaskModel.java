package com.example.Kanban.model;


import com.example.Kanban.Entity.Enum.TaskPriority;
import com.example.Kanban.Entity.Enum.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class TaskModel {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date data = new Date();
    @Setter
    @Getter
    private TaskStatus status;
    @Setter
    @Getter
    private TaskPriority priority;


}
