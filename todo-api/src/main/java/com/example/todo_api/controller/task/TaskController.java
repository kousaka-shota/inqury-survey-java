package com.example.todo_api.controller.task;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo_api.controller.TasksApi;
import com.example.todo_api.controller.model.TaskDTO;
import com.example.todo_api.controller.model.TaskForm;
import com.example.todo_api.service.task.TaskEntity;
import com.example.todo_api.service.task.TaskService;

import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
// @RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;

    @Override
    public  ResponseEntity<TaskDTO> showTask(Long taskId) {
        TaskEntity task = taskService.find(taskId);
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm taskForm){
        TaskEntity entity =  taskService.create(taskForm.getTitle());
        TaskDTO task = new TaskDTO();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        return ResponseEntity
        // URIのロケーションヘッダーを作成している
            .created(URI.create("/tasks/" + task.getId()))
            .body(task);
    }
}
