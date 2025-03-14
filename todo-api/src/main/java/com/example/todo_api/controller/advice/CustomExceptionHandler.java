package com.example.todo_api.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.todo_api.controller.model.ResourceNotFoundError;
import com.example.todo_api.service.task.TaskEntityNotFoundException;

// adviceとはspringが処理している途中に何かあれば↓の処理を差し込むこと
@RestControllerAdvice
public class CustomExceptionHandler {
    
    // annotationでどのエラーに対して実行するかを指定する
    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException e){
        ResourceNotFoundError error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());
        // 404statusを返して、bodyにResourceNotFoundをつめる
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error);
    }
}
