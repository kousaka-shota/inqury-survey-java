package com.example.demo.app.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping
    public String index(TaskForm taskForm,Model model) {
        model.addAttribute("title", "Home");
        model.addAttribute("taskForm", taskForm);

        List<Task> list = service.findAll();
        model.addAttribute("list", list);

        return "task/index";
    }

    @PostMapping("/insert")
    public String insert(Model model) {
        //TODO: process POST request
        
        return "";
    }
    
    @PostMapping("/update")
    public String update(Model model) {
        //TODO: process POST request
        
        return "";
    }
    @PostMapping("/delete")
    public String delete(Model model) {
        //TODO: process POST request
        
        return "";
    }

    


}
