package com.example.demo.repository;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Survey;

@Repository
public class SurveyDaoImpl implements SurveyDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SurveyDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertSurvey(Survey survey){
        jdbcTemplate.update("INSERT INTO Survey(age, satisfaction, comment, created) VALUES(?, ?, ?, ?) ",
         survey.getAge(),survey.getSatisfaction(),survey.getComment(),survey.getCreated());
    };

    @Override
    public List<Survey> getAllSurveys(){
        String sql = "SELECT id, age, satisfaction, comment, created FROM survey";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ArrayList<Survey> surveyList = new ArrayList<Survey>();

        for (Map<String, Object> item : list){
            Survey survey = new Survey();
            survey.setId((int)item.get("id"));
            survey.setAge((int)item.get("age"));
            survey.setSatisfaction((int)item.get("satisfaction"));
            survey.setComment((String)item.get("comment"));
            survey.setCreated(((java.sql.Timestamp)item.get("created")).toLocalDateTime());
            surveyList.add(survey);
        }

        return surveyList;
    }

}
