package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Survey;

public interface SurveyDao {

    void insertSurvey(Survey survey);

    List<Survey> getAllSurveys();

}
