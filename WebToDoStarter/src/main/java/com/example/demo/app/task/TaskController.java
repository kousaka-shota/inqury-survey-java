package com.example.demo.app.task;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

        return "task/index";
    }

    @PostMapping("/insert")
    public String insert(@Validated TaskForm taskForm,BindingResult result,Model model) {
        if (result.hasErrors()){
            model.addAttribute("title", "タスク一覧");
            taskForm.setNewTask(true);
            model.addAttribute("taskForm", taskForm);
    
            List<Task> list = service.findAll();
            model.addAttribute("list", list);
            return "task/index";
        }
        Task task = makeTask(taskForm, 0);
        System.out.println("a");
        service.insert(task);
        System.out.println("b");

        model.addAttribute("complete", "Registered");
        // redirectは叩きたいGETメソッドのURLにする（htmlへのpathじゃない）
        return "redirect:/task";
    }
    
    @PostMapping("/update")
    public String update(
        @Valid @ModelAttribute TaskForm taskForm,
        BindingResult result,
        Model model,
        @RequestParam int taskId,
        RedirectAttributes redirectAttributes
        ) {
            System.out.println(taskId);
            Task task = makeTask(taskForm, taskId);

            if (result.hasErrors()){
                model.addAttribute("title", "タスク一覧");

                List<Task> list = service.findAll();
                model.addAttribute("list", list);

                return "task/index";
            } else {
                service.update(task);
                redirectAttributes.addFlashAttribute("complete","変更が完了しました");
                return "redirect:/task/" + taskId;
            }
        }
    @PostMapping("/delete")
    public String delete(@RequestParam int taskId,Model model) {
        service.deleteById(taskId);
        model.addAttribute("title", "タスク一覧");
        List<Task> list = service.findAll();
        model.addAttribute("list", list);
        return "redirect:/task";
    }

    @GetMapping("/{id}")
    public String showUpdate(
        TaskForm taskForm,
        @PathVariable int id,
        Model model
    ){
        Optional<Task> taskOpt = service.getTask(id);

        // taskOpt.mapでtaskoptの中身（Task）を取り出せる（tに代入される）その結果をoptionalでラップできる
        Optional<TaskForm> taskFormOpt = taskOpt.map(t -> makeTaskForm(t));

        if (taskFormOpt.isPresent()){
            taskForm = taskFormOpt.get();
            model.addAttribute("taskForm", taskForm);            
        }

        model.addAttribute("taskForm", taskForm);
        model.addAttribute("title", "更新Form");
        List<Task> list = service.findAll();
        model.addAttribute("list", list);
        model.addAttribute("taskId", id);
        return "task/index";

    }

    private Task makeTask(TaskForm taskForm, int taskId){
        Task task = new Task();
        if (taskId != 0){
            task.setId(taskId);
        }
        task.setUserId(1);
        task.setTypeId(taskForm.getTypeId());
        task.setTitle(taskForm.getTitle());
        task.setDetail(taskForm.getDetail());
        task.setDeadline(taskForm.getDeadline());
        return task;
    }

    private TaskForm makeTaskForm(Task task){
        TaskForm taskForm = new TaskForm();
        taskForm.setNewTask(false);
        taskForm.setTitle(task.getTitle());
        taskForm.setDeadline(task.getDeadline());
        taskForm.setDetail(task.getDetail());
        taskForm.setTypeId(task.getTypeId());
        return taskForm;
    }
    


}
