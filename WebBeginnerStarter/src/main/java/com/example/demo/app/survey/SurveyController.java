package com.example.demo.app.survey;

import java.time.LocalDateTime;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Survey;
import com.example.demo.service.SurveyService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService service;

    @Autowired
    public SurveyController(SurveyService service){
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        System.out.println("1");
        List<Survey> list = service.getAllSurveys();
        System.out.println(list.get(0).getComment());

        model.addAttribute("surveyList",list);
        model.addAttribute("title", "survey index");
        return "survey/index";
    }
    

    @GetMapping("/form")
    public String form(
        SurveyForm surveyForm,
        Model model
    ) {
        model.addAttribute("title","survey form");
        return "survey/form";
    }

    @PostMapping("/form")
    public String formGoBack(SurveyForm surveyForm,Model model) {
        model.addAttribute("title","survey form");
        return "survey/form";
    }

    @PostMapping("/confirm")
    public String confirm(
        @Validated SurveyForm surveyForm,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()){
            model.addAttribute("title", "survey form");
            return "survey/form";
        }
        model.addAttribute("title", "confirm");        
        return "survey/confirm";
    }

    @PostMapping("/complete")
    public String complete(
        @Validated SurveyForm surveyForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()){
            model.addAttribute("title", "survey form");
            return "survey/form";
        }
        
        Survey survey = new Survey();
        survey.setAge(surveyForm.getAge());
        survey.setComment(surveyForm.getComment());
        survey.setSatisfaction(surveyForm.getSatisfaction());
        survey.setCreated(LocalDateTime.now());
        
        service.save(survey);
        redirectAttributes.addFlashAttribute("complete","Registered");
        
        return "redirect:/survey/form";
    }
    
    
    

}
