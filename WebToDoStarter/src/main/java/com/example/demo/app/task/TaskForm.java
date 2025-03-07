package com.example.demo.app.task;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class TaskForm {

    @Digits(integer = 1, fraction = 0)
    @NotNull(message = "選択してください")
    private int typeId;

    @NotNull(message = "入力してください")
    @Size(max=20,min=1,message = "20文字以内で入力してください")
    private String title;

    @NotNull(message = "入力してください")
    private String detail;

    @NotNull(message = "期限を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "過去の日程が入力されています")
    private LocalDateTime deadline;

    private boolean newTask;

    public int getTypeId(){
        return this.typeId;
    }
    public void setTypeId(int typeId){
        this.typeId = typeId;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDetail(){
        return this.detail;
    }
    public void setDetail(String detail){
        this.detail = detail;
    }
    public LocalDateTime getDeadline(){
        return this.deadline;
    }
    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }
    public boolean getNewTask(){
        return this.newTask;
    }
    public void setNewTask(boolean newTask){
        this.newTask = newTask;
    }
}
