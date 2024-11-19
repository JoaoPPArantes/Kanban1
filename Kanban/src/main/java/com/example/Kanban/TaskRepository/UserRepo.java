package com.example.Kanban.TaskRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Kanban.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
}
