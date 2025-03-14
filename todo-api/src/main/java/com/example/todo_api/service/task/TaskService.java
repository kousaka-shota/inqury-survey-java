package com.example.todo_api.service.task;

import com.example.todo_api.controller.HealthController;
import com.example.todo_api.repository.task.TaskRecord;
import com.example.todo_api.repository.task.TaskRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    
    public TaskEntity find(){
        return taskRepository.select()
        .map(record -> new TaskEntity(record.getId(),record.getTitle()))
        .orElseThrow(()->new IllegalStateException("TODO"));
    }

}
