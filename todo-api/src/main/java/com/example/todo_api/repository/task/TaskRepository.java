package com.example.todo_api.repository.task;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface TaskRepository {
    
    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(Long taskId);

    // autoIncrementしたidをSQLの返り値にセットしてくれる
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    // autoIncrementされた値を取得するには返り値の型がvoidじゃないといけない
    void insert(TaskRecord record);

    @Select("SELECT id, title FROM tasks")
    List<TaskRecord> selectList();

    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);

}
