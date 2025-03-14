package com.example.todo_api.repository.task;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskRepository {
    
    @Select("SELECT id, title FROM tasks ORDER BY id LIMIT 1")
    Optional<TaskRecord> select();

}
