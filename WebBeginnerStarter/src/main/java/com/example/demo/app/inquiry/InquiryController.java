package com.example.demo.app.inquiry;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService){
        this.inquiryService = inquiryService;
    }

    @GetMapping
    // modelはControllerが処理したものをJSPに渡すもの（次のリクエストには引き継がれない）
    public  String index(Model model) {
        List<Inquiry> list = inquiryService.getAll();
        model.addAttribute("inquiryList", list);
        model.addAttribute("title", "inquiry index");
        return "inquiry/index";
    }
    

    @GetMapping("/form")
    public String form(InquiryForm inquiryForm,
    @ModelAttribute("complete") String complete,
    Model model) {
        model.addAttribute("title", "in");
        return "inquiry/form";
    }

    @PostMapping("/confirm")
    public String confirm(
        // InquiryFormで書いたvalidationを有効にするために必要
        @Validated InquiryForm inquiryForm,
        // validationを書けた結果が入る
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()){
            model.addAttribute("title", "inquiry");
            return "inquiry/form";
        }
        model.addAttribute("title", "confirm");
        return "inquiry/confirm";
    }

    @PostMapping("/form")
    public String formGoBack(InquiryForm inquiryForm, Model model) {
        model.addAttribute("title", "inquiry");
        return "inquiry/form";
    }

    @PostMapping("/complete")
    public String complete(
        // validateの実行
        @Validated InquiryForm inquiryForm,
        // validateの結果が入る
        BindingResult result,
        Model model,
        // 二重クリック防止
        RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()){
            model.addAttribute("title", "inquiry");
            return "inquiry/form";
        }

        // Serviceに渡すためのInquiryを作成（Formから詰め変える）
        Inquiry inquiry = new Inquiry();
        inquiry.setName(inquiryForm.getName());
        inquiry.setEmail(inquiryForm.getEmail());
        inquiry.setContents(inquiryForm.getContents());
        inquiry.setCreated(LocalDateTime.now());

        // Serviceのメソッドでデータを登録
        inquiryService.save(inquiry);

        // Modelではリダイレクト時にデータが失われるため、リダイレクト先でもデータを使えるようにするためにredirectAttributesを用いている
        redirectAttributes.addFlashAttribute("complete","Registered");
        return "redirect:/inquiry/form";
    }
    
    
    
    

}
