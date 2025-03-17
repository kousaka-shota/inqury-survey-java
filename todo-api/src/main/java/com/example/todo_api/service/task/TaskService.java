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
    
    public TaskEntity find(Long taskId){
        return taskRepository.select(taskId)
        .map(record -> new TaskEntity(record.getId(),record.getTitle()))
        .orElseThrow(()->new TaskEntityNotFoundException(taskId));
    }

    public TaskEntity create(String title){
        TaskRecord record = new TaskRecord(null, title);
        // こうするだけでrecordには勝手にincrementされたidが入ってくる（repositoryで実装済みのため）
        taskRepository.insert(record);

        TaskEntity entity = new TaskEntity(record.getId(), record.getTitle());
        return entity;
    }

}
