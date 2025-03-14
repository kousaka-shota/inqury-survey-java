package com.example.todo_api.controller.task;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo_api.controller.TasksApi;
import com.example.todo_api.controller.model.TaskDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
// @RequestMapping("/tasks")
public class TaskController implements TasksApi {

    @Override
    public  ResponseEntity<TaskDTO> showTask() {
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setTitle("test");
        return ResponseEntity.ok(task);

    }
}
