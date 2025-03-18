package com.example.todo_api.controller.task;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo_api.controller.TasksApi;
import com.example.todo_api.controller.model.TaskDTO;
import com.example.todo_api.controller.model.TaskForm;
import com.example.todo_api.controller.model.TaskListDTO;
import com.example.todo_api.service.task.TaskEntity;
import com.example.todo_api.service.task.TaskService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class TaskController implements TasksApi {

    private final TaskService taskService;

    @Override
    public  ResponseEntity<TaskDTO> showTask(Long taskId) {
        TaskEntity task = taskService.find(taskId);
        
        TaskDTO dto = toTaskDTO(task);
        
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm taskForm){
        TaskEntity entity =  taskService.create(taskForm.getTitle());
        TaskDTO task = toTaskDTO(entity);

        return ResponseEntity
        // URIのロケーションヘッダーを作成している
            .created(URI.create("/tasks/" + task.getId()))
            .body(task);
    }

    @Override
    public ResponseEntity<TaskListDTO> listTasks(){
        List<TaskEntity> taskList = taskService.find();
        TaskListDTO dto = new TaskListDTO();
        List<TaskDTO> dtoList = taskList.stream().map(
            this::toTaskDTO
        ).collect(Collectors.toList());

        dto.setResults(dtoList);
        
        return ResponseEntity.ok().body(dto);
    }

    public TaskDTO toTaskDTO(TaskEntity taskEntity){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEntity.getId());
        taskDTO.setTitle(taskEntity.getTitle());
        return taskDTO;
    }
        
}


