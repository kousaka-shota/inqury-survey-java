package com.example.todo_api.controller.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.todo_api.controller.model.BadRequestError;
import com.example.todo_api.controller.model.InvalidParams;

public class BadRequestErrorCreator {

    public static BadRequestError from(
        MethodArgumentNotValidException ex
        ){
            List<InvalidParams> list = ex.getFieldErrors()
                .stream()
                .map(fieldError -> {
                    InvalidParams param = new InvalidParams();
                    param.setName(fieldError.getField());
                    param.setReason(fieldError.getDefaultMessage());
                    return param;
                }
            ).collect(Collectors.toList());
          BadRequestError error = new BadRequestError();
          error.setInvalidParams(list);
          return error;
        }
}

