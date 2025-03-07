package com.example.demo.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Task;
import com.example.demo.entity.TaskType;

@Repository
public class TaskDaoImpl implements TaskDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public List<Task> findAll(){
        String sql = "SELECT task.id, user_id, type_id, title, detail, deadline, type, comment FROM task INNER JOIN task_type ON task.type_id = task_type.id";
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql);
        ArrayList<Task> taskList = new ArrayList<Task>();

        for (Map<String,Object> item : resultList){
            Task task = new Task();
            task.setId((int)item.get("id"));
            task.setUserId((int)item.get("user_id"));
            task.setTypeId((int)item.get("type_id"));
            task.setTitle((String)item.get("title"));
            task.setDetail((String)item.get("detail"));
            task.setDeadline(((Timestamp)item.get("deadline")).toLocalDateTime());
            TaskType type = new TaskType();
            type.setId((int)item.get("id"));
            type.setType((String)item.get("type"));
            type.setComment((String)item.get("comment"));

            task.setTaskType(type);

            taskList.add(task);
        }
        return taskList;
     }

     @Override
     public Optional<Task> findById(int id){
        Map<String, Object> map = jdbcTemplate.queryForMap(
            "SELECT * FROM task INNER JOIN task_type ON task.type_id = task_type.id WHERE task.id = ?",
            id);//?の部分に第二引数以降のデータが入る→プリペアードステートメントという。SQLインジェクション対策
        
        Task task = new Task();
        task.setId((int)map.get("id"));
        task.setUserId((int)map.get("userId"));
        task.setTypeId((int)map.get("typeId"));
        task.setTitle((String)map.get("title"));
        task.setDetail((String)map.get("detail"));
        task.setDeadline(((Timestamp)map.get("deadline")).toLocalDateTime());

        TaskType type = new TaskType();
        type.setId((int)map.get("type_id"));
        type.setType((String)map.get("type"));
        type.setComment((String)map.get("comment"));

        task.setTaskType(type);

        Optional<Task> taskOpt = Optional.ofNullable(task);
        
        return taskOpt;

     }

     @Override
     public void insert(Task task){
        jdbcTemplate.update(
            "INSERT INTO Task(user_id, type_id, taskType, title, detail, deadline) VALUES(?, ?, ?, ?, ?, ?)",
            task.getUserId(),task.getTypeId(),task.getTaskType(),task.getTitle(),task.getDetail(),task.getDeadline());
     }

     @Override
    //  updateするデータがなかった場合0を返すため返り値の型はintになる（0だったら例外処理とかを実装できる）
     public int update(Task task){
        return jdbcTemplate.update(
            "UPDATE task SET type_id = ?, title = ?, detail = ?, deadline = ? WHERE id = ?",
            task.getTypeId(),task.getTitle(),task.getDetail(),task.getDeadline());
     }

     @Override
     public int deleteById(int id){
        return jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
     }

     @Override
     public List<Task> findByType(int typeId){
        return null;
     }

}
