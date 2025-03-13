package com.example.todo_api.service.sample;

import org.springframework.stereotype.Service;

import com.example.todo_api.repository.sample.SampleRecord;
import com.example.todo_api.repository.sample.SampleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository repository;

    public SampleEntity find(){
        SampleRecord record = repository.select();
        return new SampleEntity(record.getContent());
    }


}
