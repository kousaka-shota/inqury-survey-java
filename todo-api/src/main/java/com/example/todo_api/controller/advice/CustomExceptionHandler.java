package com.example.todo_api.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.todo_api.controller.model.BadRequestError;
import com.example.todo_api.controller.model.ResourceNotFoundError;
import com.example.todo_api.service.task.TaskEntityNotFoundException;

// adviceとはspringが処理している途中に何かあれば↓の処理を差し込むこと
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
    
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers, 
        HttpStatusCode status,
        WebRequest request
        ) {
        BadRequestError error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }

}
