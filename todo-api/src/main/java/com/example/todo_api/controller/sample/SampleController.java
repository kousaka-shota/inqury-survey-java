package com.example.todo_api.controller.sample;

import org.springframework.web.bind.annotation.RestController;

import com.example.todo_api.service.sample.SampleEntity;
import com.example.todo_api.service.sample.SampleService;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/samples")
public class SampleController {

    private final SampleService service;

    public SampleController(SampleService service){
        this.service = service;
    }    

    @GetMapping
    public SampleDTO index() {
        SampleEntity data = service.find();
        return new SampleDTO(data.getContent(),LocalDateTime.now());
    }
    
}
