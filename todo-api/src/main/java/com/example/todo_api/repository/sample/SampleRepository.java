package com.example.todo_api.repository.sample;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

// @Repository
// mybatisとして扱うためにMapperとinterfaceにする
@Mapper
public interface SampleRepository {

    // Mybatisの書き方（DAOとは異なり、SQLをここに書くことができる
    @Select("SELECT content FROM samples ORDER BY id LIMIT 1;")
    public SampleRecord select();

    // public SampleRecord select(){
    //     return new SampleRecord("world");
    // }

}
