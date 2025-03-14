package com.example.todo_api.service.task;

public class TaskEntityNotFoundException extends RuntimeException {
    private Long taskId;
    public TaskEntityNotFoundException(Long taskId){
        super("task not found" + taskId);
        this.taskId = taskId;
    }
}
