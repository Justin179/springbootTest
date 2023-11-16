package com.justin.springboottest;

import com.justin.springboottest.models.CollegeStudent;
import com.justin.springboottest.models.Grade;
import com.justin.springboottest.models.HistoryGrade;
import com.justin.springboottest.models.MathGrade;
import com.justin.springboottest.models.ScienceGrade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringbootTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTestApplication.class, args);
    }
    @Bean
    @Scope(value = "prototype")
    CollegeStudent getCollegeStudent() {
        return new CollegeStudent();
    }

    @Bean
    @Scope(value = "prototype")
    Grade getMathGrade(double grade) {
        return new MathGrade(grade);
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("mathGrades")
    MathGrade getGrade() {
        return new MathGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("scienceGrades")
    ScienceGrade getScienceGrade() {
        return new ScienceGrade();
    }

    @Bean
    @Scope(value = "prototype")
    @Qualifier("historyGrades")
    HistoryGrade getHistoryGrade() {
        return new HistoryGrade();
    }
}
