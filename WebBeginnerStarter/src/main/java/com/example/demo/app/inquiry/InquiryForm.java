package com.example.demo.app.inquiry;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class InquiryForm {

    @Size(min=1,max=20,message="please input 20char or less")
    private String name;
    
    @NotNull
    @Email(message="Invalid")
    private String email;

    @NotNull
    private String contents;

    public InquiryForm(){}
    
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;

    }public String getContents(){
        return contents;
    }

    public void setContents(String contents){
        this.contents = contents;
    }


}
