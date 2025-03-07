package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskDao;
import com.example.demo.entity.Task;

@Service
public class TaskServiceImpl implements TaskService{

    // 実装クラスではなく、interfaceを注入する。（impleを入れるとテストの時にクラスの差し替えが発生する。interfaceと実装クラスは@Autowiredで紐づけられている）
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao){
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> findAll(){
        return taskDao.findAll();

    }
    @Override
    public Optional<Task> getTask(int id){
        try {
            return taskDao.findById(id);
        } catch (EmptyResultDataAccessException e){
            throw new TaskNotFoundException("指定されたタスクが存在しません");
        }
    }

    @Override
    public void insert(Task task){
        taskDao.insert(task);
    }
    @Override
    public void update(Task task){
        int result = taskDao.update(task);
        if (result == 0){
            throw new TaskNotFoundException("更新するタスクが存在しまっせん");
        }
    }
    @Override
    public void deleteById(int id){
        int result = taskDao.deleteById(id);
        if (result == 0){
            throw new TaskNotFoundException("削除するタスクが存在しません");
        }
    }
    @Override
    public List<Task> findByType(int typeId){
        return null;
    }

}
