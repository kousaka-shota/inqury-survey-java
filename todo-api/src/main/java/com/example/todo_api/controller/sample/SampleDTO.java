package com.example.todo_api.controller.sample;

import java.time.LocalDateTime;

import lombok.Value;

// @Valueで勝手にconstractorやgetter,setterが生成される（lombokの機能）
@Value
public class SampleDTO {
    String content;
    LocalDateTime timestamp;

    // public SampleDTO(String content,LocalDateTime timestamp){
    //     this.content = content;
    //     this.timestamp = timestamp;
    // }

    // public String getContent(){
    //     return content;
    // }
    // public void setContent(String content){
    //     this.content = content;
    // }
    // public LocalDateTime getTimestamp(){
    //     return timestamp;
    // }
    // public void setTimestamp(LocalDateTime timestamp){
    //     this.timestamp = timestamp;
    // }
}
