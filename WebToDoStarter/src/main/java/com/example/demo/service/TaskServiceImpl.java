package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Task> taskOpt = taskDao.findById(id);
        if (taskOpt.isPresent()){
            return taskOpt;
        }
        return null;
    }

    @Override
    public void insert(Task task){
        taskDao.insert(task);
    }
    @Override
    public void update(Task task){
        taskDao.update(task);
    }
    @Override
    public void deleteById(int id){
        taskDao.deleteById(id);
    }
    @Override
    public List<Task> findByType(int typeId){
        return null;
    }

}
