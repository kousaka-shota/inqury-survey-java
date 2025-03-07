package com.example.demo.app.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
        model.addAttribute("title", "タスク一覧");
        taskForm.setNewTask(true);
        model.addAttribute("taskForm", taskForm);

        List<Task> list = service.findAll();
        model.addAttribute("list", list);
        System.out.println(list.get(0).getId());

        return "task/index";
    }

    @PostMapping("/insert")
    public String insert(@Validated TaskForm taskForm,BindingResult result,Model model) {
        if (result.hasErrors()){
            model.addAttribute("title", "タスク一覧");
            return "task/index";
        }
        Task task = new Task();
        // userIdをどうするか不明
        // task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
        // taskTypeをidからgetしてきてsetする
        
        service.insert(task);
        model.addAttribute("complete", "Registered");
        return "task/index";
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
